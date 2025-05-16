package com.bagservice.service.impl;

import com.bagservice.dto.*;
import com.bagservice.model.*;
import com.bagservice.repository.*;
import com.bagservice.service.BagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the BagService interface
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BagServiceImpl implements BagService {

    private final BagRepository bagRepository;
    private final PickupBookingRepository pickupBookingRepository;
    private final DropoffBookingRepository dropoffBookingRepository;
    private final TrackingEventRepository trackingEventRepository;
    private final BagInsuranceRepository bagInsuranceRepository;
    private final InsuranceClaimRepository insuranceClaimRepository;

    @Override
    @Transactional
    public PickupBookingResponse createPickupBooking(PickupBookingRequest request) {
        log.info("Creating pickup booking for bag tag: {}", request.getBagTag());
        
        // Find bag by tag
        Optional<Bag> bagOptional = bagRepository.findByBagTag(request.getBagTag());

        Bag bag;
        if (!bagOptional.isPresent()) {
            // create a new bag
            log.info("Bag not found with tag {}, creating new bag", request.getBagTag());
            
            // Validate the bag creation fields
            if (request.getFlightNumber() == null || request.getFlightDate() == null || 
                request.getAirline() == null || request.getOrigin() == null || request.getDestination() == null) {
                throw new RuntimeException("Missing required bag information for creating a new bag");
            }
            
            bag = Bag.builder()
                .bagTag(request.getBagTag())
                .status(BagStatus.AVAILABLE)
                .passengerName(request.getContactName())
                .flightNumber(request.getFlightNumber())
                .flightDate(request.getFlightDate())
                .airline(request.getAirline())
                .origin(request.getOrigin())
                .destination(request.getDestination())
                .weight(request.getWeight() != null ? request.getWeight() : 0.0) // Default to 0 if not provided
                .length(request.getLength() != null ? request.getLength() : 0.0)
                .width(request.getWidth() != null ? request.getWidth() : 0.0)
                .height(request.getHeight() != null ? request.getHeight() : 0.0)
                .build();
            bagRepository.save(bag);
            log.info("Created new bag with ID: {}", bag.getId());
        } else {
            bag = bagOptional.get();
        }
        
        // Create pickup booking
        PickupBooking booking = PickupBooking.builder()
                .bag(bag)
                .pickupLocation(request.getPickupLocation())
                .pickupTime(request.getPickupTime())
                .contactPhone(request.getContactPhone())
                .specialInstructions(request.getSpecialInstructions())
                .status(BookingStatus.SCHEDULED)
                .createdAt(LocalDateTime.now())
                .build();
        
        pickupBookingRepository.save(booking);
        
        // Create tracking event for the pickup booking
        createTrackingEvent(bag, "Pickup booking created", "Pickup scheduled for: " + request.getPickupTime(), bag.getStatus());
        
        return PickupBookingResponse.builder()
                .id(booking.getId())
                .bagTag(bag.getBagTag())
                .pickupLocation(booking.getPickupLocation())
                .pickupTime(booking.getPickupTime())
                .contactPhone(booking.getContactPhone())
                .status(booking.getStatus())
                .createdAt(booking.getCreatedAt())
                .message("Pickup booking created successfully")
                .build();
    }

    @Override
    @Transactional
    public DropoffBookingResponse createDropoffBooking(DropoffBookingRequest request) {
        log.info("Creating dropoff booking for bag tag: {}", request.getBagTag());
        
        // Find bag by tag
        Bag bag = bagRepository.findByBagTag(request.getBagTag())
                .orElseThrow(() -> new RuntimeException("Bag not found with tag: " + request.getBagTag()));
        
        // Create dropoff booking
        DropoffBooking booking = DropoffBooking.builder()
                .bag(bag)
                .dropoffLocation(request.getDropoffLocation())
                .dropoffTime(request.getDropoffTime())
                .contactPhone(request.getContactPhone())
                .specialInstructions(request.getSpecialInstructions())
                .status(BookingStatus.SCHEDULED)
                .createdAt(LocalDateTime.now())
                .build();
        
        dropoffBookingRepository.save(booking);
        
        // Create tracking event for the dropoff booking
        createTrackingEvent(bag, "Dropoff booking created", "Dropoff scheduled for: " + request.getDropoffTime(), bag.getStatus());
        
        return DropoffBookingResponse.builder()
                .id(booking.getId())
                .bagTag(bag.getBagTag())
                .dropoffLocation(booking.getDropoffLocation())
                .dropoffTime(booking.getDropoffTime())
                .contactPhone(booking.getContactPhone())
                .status(booking.getStatus())
                .createdAt(booking.getCreatedAt())
                .message("Dropoff booking created successfully")
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public TrackBagResponse trackBag(TrackBagRequest request) {
        log.info("Tracking bag with tag: {}", request.getBagTag());
        
        // Find bag by tag
        Bag bag = bagRepository.findByBagTag(request.getBagTag())
                .orElseThrow(() -> new RuntimeException("Bag not found with tag: " + request.getBagTag()));
        
        // Get all tracking events
        List<TrackingEvent> events = trackingEventRepository.findByBagOrderByTimestampDesc(bag);
        
        // Get latest event if available
        Optional<TrackingEvent> latestEvent = events.isEmpty() ? Optional.empty() : Optional.of(events.get(0));
        
        return TrackBagResponse.builder()
                .bagTag(bag.getBagTag())
                .currentStatus(bag.getStatus())
                .currentLocation(latestEvent.map(TrackingEvent::getLocation).orElse("Unknown"))
                .flightNumber(bag.getFlightNumber())
                .airline(bag.getAirline())
                .origin(bag.getOrigin())
                .destination(bag.getDestination())
                .passengerName(bag.getPassengerName())
                .trackingHistory(events.stream()
                        .map(event -> TrackingEventDto.builder()
                                .id(event.getId())
                                .timestamp(event.getTimestamp())
                                .location(event.getLocation())
                                .description(event.getDescription())
                                .status(event.getStatus())
                                .build())
                        .toList())
                .message("Bag tracking information retrieved successfully")
                .build();
    }

    @Override
    @Transactional
    public BagInsuranceQuoteResponse bagInsuranceQuote(BagInsuranceQuoteRequest request) {
        log.info("Getting insurance quote for flight: {}", request.getFlightNumber());
        
        // Calculate premium based on dimensions and declared value
        double volume = request.getLength() * request.getWidth() * request.getHeight();
        BigDecimal premium = calculatePremium(request.getWeight(), volume, 1000.0); // Assuming default value of $1000
        BigDecimal coverageLimit = BigDecimal.valueOf(1000.0); // Default coverage
        String quoteId = generateQuoteId();
        
        return BagInsuranceQuoteResponse.builder()
                .premiumAmount(premium)
                .coverageLimit(coverageLimit)
                .quoteId(quoteId)
                .validUntil(LocalDateTime.now().plusHours(24).format(DateTimeFormatter.ISO_DATE_TIME))
                .message("Insurance quote generated successfully")
                .build();
    }

    @Override
    @Transactional
    public BagInsureResponse bagInsure(BagInsureRequest request) {
        log.info("Insuring bag with tag: {}", request.getBagTag());
        
        // Find bag by tag
        Bag bag = bagRepository.findByBagTag(request.getBagTag())
                .orElseThrow(() -> new RuntimeException("Bag not found with tag: " + request.getBagTag()));
        
        // Check if bag already has insurance
        if (bagInsuranceRepository.findByBag(bag).isPresent()) {
            throw new RuntimeException("Bag already has insurance coverage");
        }
        
        // Create insurance policy
        BagInsurance insurance = BagInsurance.builder()
                .bag(bag)
                .premiumAmount(request.getPremiumAmount())
                .coverageLimit(request.getCoverageLimit())
                .policyNumber(generatePolicyNumber())
                .policyStartDate(LocalDateTime.now())
                .policyEndDate(LocalDateTime.now().plusDays(30)) // 30-day coverage
                .status(InsuranceStatus.ACTIVE)
                .build();
        
        bagInsuranceRepository.save(insurance);
        
        // Create tracking event for the insurance
        createTrackingEvent(bag, "Bag insured", "Insured for value: " + request.getCoverageLimit(), bag.getStatus());
        
        return BagInsureResponse.builder()
                .id(insurance.getId())
                .policyNumber(insurance.getPolicyNumber())
                .bagTag(bag.getBagTag())
                .premiumAmount(insurance.getPremiumAmount())
                .coverageLimit(insurance.getCoverageLimit())
                .policyStartDate(insurance.getPolicyStartDate())
                .policyEndDate(insurance.getPolicyEndDate())
                .status(insurance.getStatus())
                .message("Bag insurance policy created successfully")
                .build();
    }

    @Override
    @Transactional
    public BagClaimResponse bagClaim(BagClaimRequest request) {
        log.info("Processing claim for bag tag: {}", request.getBagTag());
        
        // Find bag by tag
        Bag bag = bagRepository.findByBagTag(request.getBagTag())
                .orElseThrow(() -> new RuntimeException("Bag not found with tag: " + request.getBagTag()));
        
        // Find insurance for the bag
        BagInsurance insurance = bagInsuranceRepository.findByBag(bag)
                .orElseThrow(() -> new RuntimeException("No insurance found for bag with tag: " + request.getBagTag()));
        
        // Validate insurance is active
        if (insurance.getStatus() != InsuranceStatus.ACTIVE) {
            throw new RuntimeException("Insurance policy is not active");
        }
        
        // Create claim
        InsuranceClaim claim = InsuranceClaim.builder()
                .insurance(insurance)
                .claimAmount(request.getClaimAmount())
                .reason(request.getReason())
                .description(request.getDescription())
                .status(ClaimStatus.SUBMITTED)
                .submittedAt(LocalDateTime.now())
                .build();
        
        insuranceClaimRepository.save(claim);
        
        // Create tracking event for the claim
        createTrackingEvent(insurance.getBag(), "Claim submitted", 
                "Claim submitted for policy: " + insurance.getPolicyNumber(), insurance.getBag().getStatus());
        
        return BagClaimResponse.builder()
                .id(claim.getId())
                .bagTag(insurance.getBag().getBagTag())
                .policyNumber(insurance.getPolicyNumber())
                .claimAmount(claim.getClaimAmount())
                .reason(claim.getReason())
                .description(claim.getDescription())
                .status(claim.getStatus())
                .submittedAt(claim.getSubmittedAt())
                .message("Claim submitted successfully")
                .build();
    }
    
    // Helper methods
    
    private void createTrackingEvent(Bag bag, String description, String details, BagStatus status) {
        TrackingEvent event = TrackingEvent.builder()
                .bag(bag)
                .timestamp(LocalDateTime.now())
                .location("System") // Default location for system events
                .description(description)
                .status(status)
                .build();
        
        trackingEventRepository.save(event);
    }
    
    private String generateBookingReference() {
        return "BK" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    private String generatePolicyNumber() {
        return "POL" + UUID.randomUUID().toString().substring(0, 10).toUpperCase();
    }
    
    private String generateClaimReference() {
        return "CLM" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    private String generateQuoteId() {
        return "QUO" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    private BigDecimal calculatePremium(double weight, double volume, double declaredValue) {
        // Simple premium calculation - 5% of declared value plus factors based on weight and volume
        double weightFactor = weight / 5.0;
        double volumeFactor = volume / 10000.0;
        double premium = Math.round((0.05 * declaredValue + weightFactor + volumeFactor) * 100.0) / 100.0;
        return BigDecimal.valueOf(premium);
    }
} 
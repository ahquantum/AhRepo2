package com.bagservice.controller;

import com.bagservice.api.BagServiceApi;
import com.bagservice.dto.*;
import com.bagservice.service.BagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * REST controller for bag operations
 */
@RestController
@RequestMapping("/v1/bags")
@RequiredArgsConstructor
@Slf4j
public class BagController implements BagServiceApi {

    private final BagService bagService;

    @Override
    public ResponseEntity<PickupBookingResponse> createPickupBooking(@Valid PickupBookingRequest request) {
        log.info("Creating pickup booking for passenger: {} for bags: {}", request.getContactName(), request.getBags());
        PickupBookingResponse response = bagService.createPickupBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<DropoffBookingResponse> createDropoffBooking(@Valid DropoffBookingRequest request) {
        log.info("Creating dropoff booking for bag tag: {}", request.getBagTag());
        DropoffBookingResponse response = bagService.createDropoffBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<TrackBagResponse> trackBag(@Valid TrackBagRequest request) {
        log.info("Tracking bag with tag: {}", request.getBagTag());
        TrackBagResponse response = bagService.trackBag(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<BagInsuranceQuoteResponse> bagInsuranceQuote(@Valid BagInsuranceQuoteRequest request) {
        log.info("Generating insurance quote for bag");
        BagInsuranceQuoteResponse response = bagService.bagInsuranceQuote(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<BagInsureResponse> bagInsure(@Valid BagInsureRequest request) {
        log.info("Insuring bag with tag: {}", request.getBagTag());
        BagInsureResponse response = bagService.bagInsure(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<BagClaimResponse> bagClaim(@Valid BagClaimRequest request) {
        log.info("Processing claim for bag with tag: {}", 
                request.getBagTag() != null ? request.getBagTag() : "unknown");
        BagClaimResponse response = bagService.bagClaim(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
} 
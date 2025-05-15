package com.bagservice.service;

import com.bagservice.dto.*;

/**
 * Service interface for bag operations
 */
public interface BagService {

    /**
     * Create a pickup booking for a bag
     * @param request the pickup booking request
     * @return the pickup booking response
     */
    PickupBookingResponse createPickupBooking(PickupBookingRequest request);

    /**
     * Create a dropoff booking for a bag
     * @param request the dropoff booking request
     * @return the dropoff booking response
     */
    DropoffBookingResponse createDropoffBooking(DropoffBookingRequest request);

    /**
     * Track a bag's location and status
     * @param request the tracking request
     * @return the tracking response with bag's current location and history
     */
    TrackBagResponse trackBag(TrackBagRequest request);

    /**
     * Get an insurance quote for a bag
     * @param request the insurance quote request
     * @return the insurance quote response
     */
    BagInsuranceQuoteResponse bagInsuranceQuote(BagInsuranceQuoteRequest request);

    /**
     * Insure a bag
     * @param request the insure request
     * @return the insurance response
     */
    BagInsureResponse bagInsure(BagInsureRequest request);

    /**
     * Submit a claim for a lost bag
     * @param request the claim request
     * @return the claim response
     */
    BagClaimResponse bagClaim(BagClaimRequest request);
} 
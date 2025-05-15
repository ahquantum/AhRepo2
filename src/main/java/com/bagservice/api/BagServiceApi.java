package com.bagservice.api;

import com.bagservice.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * API interface for Bag Service operations
 */
@Tag(name = "Bag Service API", description = "API for booking pickup/dropoff, tracking, and insuring bags")
public interface BagServiceApi {

    @Operation(summary = "Create a pickup booking", description = "Book a location and time for an agent to pick up a passenger's bag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pickup booking created successfully",
                    content = @Content(schema = @Schema(implementation = PickupBookingResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Bag not found")
    })
    @PostMapping("/pickup-booking")
    ResponseEntity<PickupBookingResponse> createPickupBooking(@Valid @RequestBody PickupBookingRequest request);

    @Operation(summary = "Create a dropoff booking", description = "Book a location and time for the passenger to collect their bag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dropoff booking created successfully",
                    content = @Content(schema = @Schema(implementation = DropoffBookingResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Bag not found")
    })
    @PostMapping("/dropoff-booking")
    ResponseEntity<DropoffBookingResponse> createDropoffBooking(@Valid @RequestBody DropoffBookingRequest request);

    @Operation(summary = "Track a bag", description = "Track the real-time location of a bag as it moves from origin to destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bag tracking information retrieved successfully",
                    content = @Content(schema = @Schema(implementation = TrackBagResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Bag not found")
    })
    @PostMapping("/track-bag")
    ResponseEntity<TrackBagResponse> trackBag(@Valid @RequestBody TrackBagRequest request);

    @Operation(summary = "Get insurance quote for a bag", description = "Provide a quote for insurance for a bag based on its physical size, weight, and flight details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Insurance quote generated successfully",
                    content = @Content(schema = @Schema(implementation = BagInsuranceQuoteResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/bag-quote")
    ResponseEntity<BagInsuranceQuoteResponse> bagInsuranceQuote(@Valid @RequestBody BagInsuranceQuoteRequest request);

    @Operation(summary = "Insure a bag", description = "Record insurance for a bag based on its details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bag insured successfully",
                    content = @Content(schema = @Schema(implementation = BagInsureResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Bag not found"),
            @ApiResponse(responseCode = "409", description = "Insurance already exists for this bag")
    })
    @PostMapping("/bag-insure")
    ResponseEntity<BagInsureResponse> bagInsure(@Valid @RequestBody BagInsureRequest request);

    @Operation(summary = "Claim for lost bag", description = "Claim insurance compensation for a lost bag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Claim submitted successfully",
                    content = @Content(schema = @Schema(implementation = BagClaimResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Bag or insurance not found")
    })
    @PostMapping("/bag-claim")
    ResponseEntity<BagClaimResponse> bagClaim(@Valid @RequestBody BagClaimRequest request);
} 
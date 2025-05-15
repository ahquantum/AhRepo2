package com.bagservice.dto;

import com.bagservice.model.BagStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for track bag response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackBagResponse {
    
    private String bagTag;
    private String passengerName;
    private String flightNumber;
    private String airline;
    private String origin;
    private String destination;
    private BagStatus currentStatus;
    private String currentLocation;
    private List<TrackingEventDto> trackingHistory;
    private String message;
} 
package com.bagservice.dto;

import com.bagservice.model.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for dropoff booking response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DropoffBookingResponse {
    
    private Long id;
    private String bagTag;
    private String dropoffLocation;
    private LocalDateTime dropoffTime;
    private String contactPhone;
    private String specialInstructions;
    private BookingStatus status;
    private LocalDateTime createdAt;
    private String message;
} 
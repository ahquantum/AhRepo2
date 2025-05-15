package com.bagservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO for dropoff booking request
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DropoffBookingRequest {
    
    @NotBlank(message = "Bag tag is required")
    @Size(min = 10, max = 10, message = "Bag tag must be exactly 10 digits")
    @Pattern(regexp = "^[0-9]{10}$", message = "Bag tag must be a 10-digit number")
    private String bagTag;
    
    @NotBlank(message = "Dropoff location is required")
    private String dropoffLocation;
    
    @NotNull(message = "Dropoff time is required")
    @Future(message = "Dropoff time must be in the future")
    private LocalDateTime dropoffTime;
    
    @NotBlank(message = "Contact phone number is required")
    @Pattern(regexp = "^[0-9+\\-\\s]+$", message = "Invalid phone number format")
    private String contactPhone;
    
    private String specialInstructions;
} 
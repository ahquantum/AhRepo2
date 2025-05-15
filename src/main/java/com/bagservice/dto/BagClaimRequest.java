package com.bagservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for bag claim request
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BagClaimRequest {
    
    @Size(min = 10, max = 10, message = "Bag tag must be exactly 10 digits")
    @Pattern(regexp = "^[0-9]{10}$", message = "Bag tag must be a 10-digit number")
    private String bagTag;
    
    private String passengerName;
    
    @NotBlank(message = "Flight number is required")
    private String flightNumber;
    
    @NotNull(message = "Flight date is required")
    private LocalDate flightDate;
    
    @NotBlank(message = "Airline is required")
    private String airline;
    
    @NotBlank(message = "Origin is required")
    private String origin;
    
    @NotBlank(message = "Destination is required")
    private String destination;
    
    @NotNull(message = "Claim amount is required")
    @Positive(message = "Claim amount must be positive")
    private BigDecimal claimAmount;
    
    @NotBlank(message = "Reason is required")
    private String reason;
    
    private String description;
} 
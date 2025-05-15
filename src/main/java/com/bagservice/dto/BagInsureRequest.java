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
 * DTO for bag insure request
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BagInsureRequest {
    
    @NotBlank(message = "Bag tag is required")
    @Size(min = 10, max = 10, message = "Bag tag must be exactly 10 digits")
    @Pattern(regexp = "^[0-9]{10}$", message = "Bag tag must be a 10-digit number")
    private String bagTag;
    
    @NotBlank(message = "Passenger name is required")
    private String passengerName;
    
    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be positive")
    private Double weight;
    
    @NotNull(message = "Length is required")
    @Positive(message = "Length must be positive")
    private Double length;
    
    @NotNull(message = "Width is required")
    @Positive(message = "Width must be positive")
    private Double width;
    
    @NotNull(message = "Height is required")
    @Positive(message = "Height must be positive")
    private Double height;
    
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
    
    @NotNull(message = "Premium amount is required")
    @Positive(message = "Premium amount must be positive")
    private BigDecimal premiumAmount;
    
    @NotNull(message = "Coverage limit is required")
    @Positive(message = "Coverage limit must be positive")
    private BigDecimal coverageLimit;
    
    private String quoteId;
} 
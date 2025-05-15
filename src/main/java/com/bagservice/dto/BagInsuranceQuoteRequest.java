package com.bagservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

/**
 * DTO for bag insurance quote request
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BagInsuranceQuoteRequest {
    
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
} 
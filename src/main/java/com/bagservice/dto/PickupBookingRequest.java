package com.bagservice.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for pickup booking request
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PickupBookingRequest {

    @NotBlank(message = "Bag tag is required")
    @Size(min = 10, max = 10, message = "Bag tag must be exactly 10 digits")
    @Pattern(regexp = "^[0-9]{10}$", message = "Bag tag must be a 10-digit number")
    private String bagTag;
    
    @NotBlank(message = "Contact name is required")
    private String contactName;
    
    @NotBlank(message = "Contact email is required")
    @Email(message = "Invalid email format")
    private String contactEmail;
    
    @NotBlank(message = "Contact phone number is required")
    @Pattern(regexp = "^[0-9+\\-\\s]+$", message = "Invalid phone number format")
    private String contactPhone;
    
    @NotBlank(message = "Pickup location is required")
    private String pickupLocation;
    
    @NotNull(message = "Pickup time is required")
    @Future(message = "Pickup time must be in the future")
    private LocalDateTime pickupTime;
    
    private String specialInstructions;
    
    // Fields needed for bag creation if bag not found
    
    @NotBlank(message = "Flight number is required when creating a new bag")
    private String flightNumber;
    
    @NotNull(message = "Flight date is required when creating a new bag")
    private LocalDate flightDate;
    
    @NotBlank(message = "Airline is required when creating a new bag")
    private String airline;
    
    @NotBlank(message = "Origin is required when creating a new bag")
    private String origin;
    
    @NotBlank(message = "Destination is required when creating a new bag")
    private String destination;
    
    @Positive(message = "Weight must be positive")
    private Double weight;
    
    @Positive(message = "Length must be positive")
    private Double length;
    
    @Positive(message = "Width must be positive")
    private Double width;
    
    @Positive(message = "Height must be positive")
    private Double height;
} 
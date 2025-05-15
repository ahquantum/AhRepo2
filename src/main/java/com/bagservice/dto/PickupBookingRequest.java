package com.bagservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for pickup booking request
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PickupBookingRequest {
    
    @NotBlank(message = "Contact name is required")
    private String contactName;
    
    @NotBlank(message = "Contact email is required")
    @Email(message = "Invalid email format")
    private String contactEmail;
    
    @NotBlank(message = "Contact phone number is required")
    @Pattern(regexp = "^[0-9+\\-\\s]+$", message = "Invalid phone number format")
    private String contactPhone;
    
    private List<BagDTO> bags;
    
    @NotBlank(message = "Pickup location is required")
    private String pickupLocation;
    
    @NotNull(message = "Pickup time is required")
    @Future(message = "Pickup time must be in the future")
    private LocalDateTime pickupTime;
    
    private String specialInstructions;

    // @NotBlank(message = "Bag tag is required")
    // @Size(min = 10, max = 10, message = "Bag tag must be exactly 10 digits")
    // @Pattern(regexp = "^[0-9]{10}$", message = "Bag tag must be a 10-digit number")
    // private String bagTag;
} 
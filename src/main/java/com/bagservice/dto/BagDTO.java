package com.bagservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
public class BagDTO {

    @NotBlank(message = "Bag identifier is required")
    private String bagIdentifier;

    @NotBlank(message = "Bag status is required")
    private String bagStatus;

    @NotBlank(message = "Bag location is required")
    private String bagLocation;

    @NotBlank(message = "Bag destination is required")
    private String bagDestination;

    @NotBlank(message = "Passenger name is required")
    private String passengerName;
    
    @NotBlank(message = "Passenger phone number is required")
    @Pattern(regexp = "^[0-9+\\-\\s]+$", message = "Invalid phone number format")
    private String passengerPhone;
    
}

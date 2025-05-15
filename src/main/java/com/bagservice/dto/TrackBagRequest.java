package com.bagservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * DTO for track bag request
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackBagRequest {
    
    @Size(min = 10, max = 10, message = "Bag tag must be exactly 10 digits")
    @Pattern(regexp = "^[0-9]{10}$", message = "Bag tag must be a 10-digit number")
    private String bagTag;
} 
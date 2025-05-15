package com.bagservice.dto;

import com.bagservice.model.BagStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for tracking event
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackingEventDto {
    
    private Long id;
    private String location;
    private String description;
    private BagStatus status;
    private LocalDateTime timestamp;
} 
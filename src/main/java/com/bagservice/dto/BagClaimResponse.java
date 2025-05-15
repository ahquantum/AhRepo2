package com.bagservice.dto;

import com.bagservice.model.ClaimStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for bag claim response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BagClaimResponse {
    
    private Long id;
    private String bagTag;
    private String policyNumber;
    private BigDecimal claimAmount;
    private String reason;
    private String description;
    private ClaimStatus status;
    private LocalDateTime submittedAt;
    private String message;
} 
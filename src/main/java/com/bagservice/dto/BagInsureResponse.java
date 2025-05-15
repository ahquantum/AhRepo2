package com.bagservice.dto;

import com.bagservice.model.InsuranceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for bag insure response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BagInsureResponse {
    
    private Long id;
    private String bagTag;
    private String policyNumber;
    private BigDecimal premiumAmount;
    private BigDecimal coverageLimit;
    private LocalDateTime policyStartDate;
    private LocalDateTime policyEndDate;
    private InsuranceStatus status;
    private String message;
} 
package com.bagservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO for bag insurance quote response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BagInsuranceQuoteResponse {
    
    private BigDecimal premiumAmount;
    private BigDecimal coverageLimit;
    private String quoteId;
    private String validUntil;
    private String message;
} 
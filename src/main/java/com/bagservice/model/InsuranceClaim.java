package com.bagservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing an insurance claim for a lost or damaged bag
 */
@Entity
@Table(name = "insurance_claims")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_id", nullable = false)
    private BagInsurance insurance;

    @NotNull
    @Positive
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal claimAmount;

    @NotBlank
    @Column(nullable = false)
    private String reason;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimStatus status = ClaimStatus.SUBMITTED;

    @Column(nullable = false)
    private LocalDateTime submittedAt = LocalDateTime.now();

    private LocalDateTime processedAt;

    @Column(precision = 10, scale = 2)
    private BigDecimal approvedAmount;
} 
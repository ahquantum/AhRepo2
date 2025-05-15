package com.bagservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing insurance for a bag
 */
@Entity
@Table(name = "bag_insurance")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BagInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bag_id", nullable = false, unique = true)
    private Bag bag;

    @NotNull
    @Positive
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal premiumAmount;

    @NotNull
    @Positive
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal coverageLimit;

    @Column(nullable = false)
    private LocalDateTime policyStartDate = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime policyEndDate;

    @Column(nullable = false)
    private String policyNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InsuranceStatus status = InsuranceStatus.ACTIVE;
} 
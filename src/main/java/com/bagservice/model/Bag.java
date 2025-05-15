package com.bagservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Entity representing a passenger's bag
 */
@Entity
@Table(name = "bags")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^[0-9]{10}$", message = "Bag tag must be a 10-digit number")
    @Column(unique = true, nullable = false, length = 10)
    private String bagTag;

    @NotNull
    @Column(nullable = false)
    private Double weight;

    @NotNull
    @Column(nullable = false)
    private Double length;

    @NotNull
    @Column(nullable = false)
    private Double width;

    @NotNull
    @Column(nullable = false)
    private Double height;

    @NotBlank
    @Column(nullable = false)
    private String passengerName;

    @NotBlank
    @Column(nullable = false)
    private String flightNumber;

    @NotNull
    @Column(nullable = false)
    private LocalDate flightDate;

    @NotBlank
    @Column(nullable = false)
    private String airline;

    @NotBlank
    @Column(nullable = false)
    private String origin;

    @NotBlank
    @Column(nullable = false)
    private String destination;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BagStatus status = BagStatus.CHECKED_IN;
    
    @OneToOne(mappedBy = "bag", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BagInsurance insurance;
} 
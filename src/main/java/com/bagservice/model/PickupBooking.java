package com.bagservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Entity representing a booking for bag pickup
 */
@Entity
@Table(name = "pickup_bookings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PickupBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bag_id", nullable = false)
    private Bag bag;

    @NotBlank
    @Column(nullable = false)
    private String pickupLocation;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime pickupTime;

    @NotBlank
    @Column(nullable = false)
    private String contactPhone;

    @Column(length = 500)
    private String specialInstructions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status = BookingStatus.SCHEDULED;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
} 
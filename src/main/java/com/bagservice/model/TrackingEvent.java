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
 * Entity representing a tracking event for a bag
 */
@Entity
@Table(name = "tracking_events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bag_id", nullable = false)
    private Bag bag;

    @NotBlank
    @Column(nullable = false)
    private String location;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BagStatus status;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();
} 
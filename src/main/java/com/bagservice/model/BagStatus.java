package com.bagservice.model;

/**
 * Enum representing the possible statuses of a bag
 */
public enum BagStatus {
    AVAILABLE,
    CHECKED_IN,
    PICKUP_SCHEDULED,
    PICKED_UP,
    IN_TRANSIT,
    ARRIVED_AT_DESTINATION,
    DROPOFF_SCHEDULED,
    DELIVERED,
    LOST,
    DAMAGED
} 
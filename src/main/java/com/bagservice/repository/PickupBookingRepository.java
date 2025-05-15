package com.bagservice.repository;

import com.bagservice.model.Bag;
import com.bagservice.model.PickupBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for PickupBooking entity
 */
@Repository
public interface PickupBookingRepository extends JpaRepository<PickupBooking, Long> {

    /**
     * Find all pickup bookings for a bag
     * @param bag the bag entity
     * @return list of pickup bookings
     */
    List<PickupBooking> findByBag(Bag bag);
    
    /**
     * Find all pickup bookings for a bag by its ID
     * @param bagId the bag ID
     * @return list of pickup bookings
     */
    List<PickupBooking> findByBagId(Long bagId);
    
    /**
     * Find the latest pickup booking for a bag
     * @param bag the bag entity
     * @return the latest pickup booking if found
     */
    Optional<PickupBooking> findFirstByBagOrderByPickupTimeDesc(Bag bag);
} 
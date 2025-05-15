package com.bagservice.repository;

import com.bagservice.model.Bag;
import com.bagservice.model.DropoffBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for DropoffBooking entity
 */
@Repository
public interface DropoffBookingRepository extends JpaRepository<DropoffBooking, Long> {

    /**
     * Find all dropoff bookings for a bag
     * @param bag the bag entity
     * @return list of dropoff bookings
     */
    List<DropoffBooking> findByBag(Bag bag);
    
    /**
     * Find all dropoff bookings for a bag by its ID
     * @param bagId the bag ID
     * @return list of dropoff bookings
     */
    List<DropoffBooking> findByBagId(Long bagId);
    
    /**
     * Find the latest dropoff booking for a bag
     * @param bag the bag entity
     * @return the latest dropoff booking if found
     */
    Optional<DropoffBooking> findFirstByBagOrderByDropoffTimeDesc(Bag bag);
} 
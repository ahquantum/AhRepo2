package com.bagservice.repository;

import com.bagservice.model.Bag;
import com.bagservice.model.TrackingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for TrackingEvent entity
 */
@Repository
public interface TrackingEventRepository extends JpaRepository<TrackingEvent, Long> {

    /**
     * Find all tracking events for a bag
     * @param bag the bag entity
     * @return list of tracking events sorted by timestamp in descending order
     */
    List<TrackingEvent> findByBagOrderByTimestampDesc(Bag bag);
    
    /**
     * Find all tracking events for a bag by ID
     * @param bagId the bag ID
     * @return list of tracking events sorted by timestamp in descending order
     */
    List<TrackingEvent> findByBagIdOrderByTimestampDesc(Long bagId);
    
    /**
     * Find the latest tracking event for a bag
     * @param bag the bag entity
     * @return the latest tracking event if found
     */
    Optional<TrackingEvent> findFirstByBagOrderByTimestampDesc(Bag bag);
} 
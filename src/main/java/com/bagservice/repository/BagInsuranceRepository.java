package com.bagservice.repository;

import com.bagservice.model.Bag;
import com.bagservice.model.BagInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for BagInsurance entity
 */
@Repository
public interface BagInsuranceRepository extends JpaRepository<BagInsurance, Long> {

    /**
     * Find insurance for a bag
     * @param bag the bag entity
     * @return the insurance if found
     */
    Optional<BagInsurance> findByBag(Bag bag);
    
    /**
     * Find insurance for a bag by ID
     * @param bagId the bag ID
     * @return the insurance if found
     */
    Optional<BagInsurance> findByBagId(Long bagId);
    
    /**
     * Find insurance by policy number
     * @param policyNumber the policy number
     * @return the insurance if found
     */
    Optional<BagInsurance> findByPolicyNumber(String policyNumber);
} 
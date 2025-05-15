package com.bagservice.repository;

import com.bagservice.model.BagInsurance;
import com.bagservice.model.ClaimStatus;
import com.bagservice.model.InsuranceClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for InsuranceClaim entity
 */
@Repository
public interface InsuranceClaimRepository extends JpaRepository<InsuranceClaim, Long> {

    /**
     * Find all claims for an insurance
     * @param insurance the insurance entity
     * @return list of claims
     */
    List<InsuranceClaim> findByInsurance(BagInsurance insurance);
    
    /**
     * Find all claims for an insurance by ID
     * @param insuranceId the insurance ID
     * @return list of claims
     */
    List<InsuranceClaim> findByInsuranceId(Long insuranceId);
    
    /**
     * Find all claims for an insurance with a specific status
     * @param insurance the insurance entity
     * @param status the claim status
     * @return list of claims with the specified status
     */
    List<InsuranceClaim> findByInsuranceAndStatus(BagInsurance insurance, ClaimStatus status);
} 
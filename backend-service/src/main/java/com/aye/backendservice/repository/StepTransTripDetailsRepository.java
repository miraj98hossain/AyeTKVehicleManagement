package com.aye.backendservice.repository;

import com.aye.entity.vehicleproject.StepTransTripDetails;
import com.aye.entity.vehicleproject.embeddable.StepTransTripDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: Miraj
 * @date: 06/05/2026
 * @time: 4:26 pm
 */
public interface StepTransTripDetailsRepository extends JpaRepository<StepTransTripDetails, StepTransTripDetailsId>, JpaSpecificationExecutor<StepTransTripDetailsId> {
}

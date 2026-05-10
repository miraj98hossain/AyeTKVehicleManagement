package com.aye.backendservice.repository;

import com.aye.entitylib.entity.vehicleproject.StepTransScaleDetails;
import com.aye.entitylib.entity.vehicleproject.embeddable.StepTransScaleDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: Miraj
 * @date: 06/05/2026
 * @time: 4:26 pm
 */
public interface StepTransScaleDetailsRepository extends JpaRepository<StepTransScaleDetails, StepTransScaleDetailsId>, JpaSpecificationExecutor<StepTransScaleDetails> {
}

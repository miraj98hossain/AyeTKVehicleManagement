package com.aye.backendservice.repository;

import com.aye.backendservice.entity.StepSetupDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

public interface StepSetupDetailsRepository extends JpaRepository<StepSetupDetails, Long>, JpaSpecificationExecutor<StepSetupDetails> {
    List<StepSetupDetails> findAllByStepSetupDetailsIdInAndIsActive(Collection<Long> stepSetupDetailsIds, Integer isActive);
}

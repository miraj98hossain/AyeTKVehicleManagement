package com.mhdev.backendservice.repository;

import com.mhdev.backendservice.entity.StepSetup;
import com.mhdev.backendservice.entity.StepSetupDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StepSetupRepository extends JpaRepository<StepSetup,Long>, JpaSpecificationExecutor<StepSetup> {
}

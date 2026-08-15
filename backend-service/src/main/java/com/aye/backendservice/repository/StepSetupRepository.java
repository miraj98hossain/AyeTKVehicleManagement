package com.aye.backendservice.repository;


import com.aye.entity.vehicleproject.StepSetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StepSetupRepository extends JpaRepository<StepSetup, Long>, JpaSpecificationExecutor<StepSetup> {
}

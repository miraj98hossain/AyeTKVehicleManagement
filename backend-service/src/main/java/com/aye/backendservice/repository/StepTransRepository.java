package com.aye.backendservice.repository;


import com.aye.entitylib.entity.vehicleproject.StepTrans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StepTransRepository extends JpaRepository<StepTrans, Long>, JpaSpecificationExecutor<StepTrans> {
}

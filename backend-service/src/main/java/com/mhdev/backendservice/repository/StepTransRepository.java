package com.mhdev.backendservice.repository;

import com.mhdev.backendservice.entity.StepTrans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StepTransRepository extends JpaRepository<StepTrans,Long>, JpaSpecificationExecutor<StepTrans> {
}

package com.mhdev.backendservice.repository;

import com.mhdev.backendservice.entity.StepTransLines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface StepTransLinesRepository extends JpaRepository<StepTransLines, Long>, JpaSpecificationExecutor<StepTransLines> {
    Optional<StepTransLines> findByParentLineId(Long parentLineId);

    Optional<StepTransLines> findByStepTransLinesNo(String stepTransLinesNo);
}

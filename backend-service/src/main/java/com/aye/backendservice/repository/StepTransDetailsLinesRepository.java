package com.aye.backendservice.repository;


import com.aye.entity.vehicleproject.StepTransDetailsLines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StepTransDetailsLinesRepository extends JpaRepository<StepTransDetailsLines, Long>, JpaSpecificationExecutor<StepTransDetailsLines> {

    List<StepTransDetailsLines> findAllByStepTransDetails_StepTransDtlId(Long stepTransDetailsStepTransDtlId);

    List<StepTransDetailsLines> findByTrnRefId(Long trnRefId);
}

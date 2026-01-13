package com.aye.backendservice.repository;


import com.aye.entitylib.entity.StepTransDetailsLines;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepTransDetailsLinesRepository extends JpaRepository<StepTransDetailsLines, Long> {

    List<StepTransDetailsLines> findAllByStepTransDetails_StepTransDtlId(Long stepTransDetailsStepTransDtlId);
}

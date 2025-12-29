package com.aye.backendservice.repository;

import com.aye.backendservice.entity.StepTransDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepTransDetailsRepository extends JpaRepository<StepTransDetails, Long> {
    StepTransDetails findByStepTrans_StepTransId(Long stepTransStepTransId);

    List<StepTransDetails> findAllByStepTrans_StepTransId(Long stepTransStepTransId);
}

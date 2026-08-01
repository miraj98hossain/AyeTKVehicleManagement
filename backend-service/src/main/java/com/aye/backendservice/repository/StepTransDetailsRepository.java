package com.aye.backendservice.repository;


import com.aye.entitylib.entity.vehicleproject.StepTransDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepTransDetailsRepository extends JpaRepository<StepTransDetails, Long> {
    StepTransDetails findByStepTrans_StepTransId(Long stepTransStepTransId);

    List<StepTransDetails> findAllByStepTrans_StepTransId(Long stepTransStepTransId);

    List<StepTransDetails> findAllByScheduleNo(String scheduleNo);

    List<StepTransDetails> findAllByOrderNumber(Long orderNumber);

    void deleteAllByStepTrans_StepTransId(Long stepTransStepTransId);

    List<StepTransDetails> findAllByOrderNumberAndStepTrans_StepTransId(Long orderNumber, Long stepTransStepTransId);

    List<StepTransDetails> findAllByScheduleNoAndStepTrans_StepTransId(String scheduleNo, Long stepTransStepTransId);
}

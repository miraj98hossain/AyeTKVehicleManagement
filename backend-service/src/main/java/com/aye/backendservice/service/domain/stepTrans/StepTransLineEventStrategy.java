package com.aye.backendservice.service.domain.stepTrans;

import com.aye.entity.user.Muser;
import com.aye.entity.vehicleproject.StepTrans;
import com.aye.entity.vehicleproject.StepTransLines;

public interface StepTransLineEventStrategy {
    boolean supports(StepTransLines reqStepTransLines);

    StepTransLines doEvent(StepTransLines reqStepTransLines, StepTransLines dbstepTransLines, StepTrans stepTrans, Muser user);
}
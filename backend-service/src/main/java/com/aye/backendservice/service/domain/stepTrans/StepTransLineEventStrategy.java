package com.aye.backendservice.service.domain.stepTrans;

import com.aye.entitylib.entity.user.Muser;
import com.aye.entitylib.entity.vehicleproject.StepTrans;
import com.aye.entitylib.entity.vehicleproject.StepTransLines;

public interface StepTransLineEventStrategy {
    boolean supports(StepTransLines reqStepTransLines);

    StepTransLines doEvent(StepTransLines reqStepTransLines, StepTransLines dbstepTransLines, StepTrans stepTrans, Muser user);
}
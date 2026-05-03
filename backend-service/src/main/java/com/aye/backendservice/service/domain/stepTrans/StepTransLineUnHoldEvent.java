package com.aye.backendservice.service.domain.stepTrans;


import com.aye.backendservice.service.StepTransLinesService;
import com.aye.entitylib.entity.user.Muser;
import com.aye.entitylib.entity.vehicleproject.StepTrans;
import com.aye.entitylib.entity.vehicleproject.StepTransLines;
import com.aye.enums.StepStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 15:31
 */
@Component
@RequiredArgsConstructor
public class StepTransLineUnHoldEvent implements StepTransLineEventStrategy {
    private final StepTransLinesService stepTransLinesService;

    @Override
    public boolean supports(StepTransLines reqStepTransLines) {
        return reqStepTransLines.getStepStatus().equals(StepStatus.L);
    }

    @Override
    public StepTransLines doEvent(StepTransLines reqStepTransLines, StepTransLines dbstepTransLines, StepTrans stepTrans, Muser user) {
        dbstepTransLines.setStepStatus(StepStatus.L);
        dbstepTransLines.setHoldBy(null);
        return this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(user.getId()));
    }
}

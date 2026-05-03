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
public class StepTransLinePickEvent implements StepTransLineEventStrategy {
    private final StepTransLinesService stepTransLinesService;


    @Override
    public boolean supports(StepTransLines reqStepTransLines) {
        return reqStepTransLines.getStepStatus().equals(StepStatus.P);
    }

    @Override
    public StepTransLines doEvent(StepTransLines reqStepTransLines, StepTransLines dbstepTransLines, StepTrans stepTrans, Muser user) {
        StepTransLines objResponse;
        //Checking it is child or not
        //if parent ? Do not need to find it's parentTrans just increment the stage
        if (dbstepTransLines.getParentLineId() == 0) {
            if (dbstepTransLines.getStage() == 1) {
                throw new IllegalArgumentException("This Step Trans is already picked");
            }
            dbstepTransLines.setStepStatus(StepStatus.P);
            dbstepTransLines.setStage(dbstepTransLines.getStage() + 1); //current value should be 1(0->1). Eligible to be at WIP now.
            objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(user.getId()));//updating

        } else {
            dbstepTransLines.setStepStatus(StepStatus.P);
            var parentTransLine = this.stepTransLinesService.getStepTransLine(dbstepTransLines.getParentLineId());
            if (parentTransLine.getStage() == 2) {
                throw new IllegalArgumentException("This Step Trans is already picked");
            }
            parentTransLine.setStage(parentTransLine.getStage() + 1); //current value should be 2(1->2). Eligible to be at com now.
            this.stepTransLinesService.saveStepTransLines(parentTransLine, false, Long.valueOf(user.getId()));//updating parent
            objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(user.getId()));//updating

        }


        return objResponse;
    }
}

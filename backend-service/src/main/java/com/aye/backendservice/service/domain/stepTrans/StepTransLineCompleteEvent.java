package com.aye.backendservice.service.domain.stepTrans;


import com.aye.backendservice.repository.StepTransRepository;
import com.aye.backendservice.service.StepTransLinesService;
import com.aye.entitylib.entity.user.Muser;
import com.aye.entitylib.entity.vehicleproject.StepTrans;
import com.aye.entitylib.entity.vehicleproject.StepTransLines;
import com.aye.enums.StepStatus;
import com.aye.enums.StepTransStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 15:31
 */
@Component
@RequiredArgsConstructor
public class StepTransLineCompleteEvent implements StepTransLineEventStrategy {
    private final StepTransRepository stepTransRepository;
    private final StepTransLinesService stepTransLinesService;

    @Override
    public boolean supports(StepTransLines reqStepTransLines) {
        return reqStepTransLines.getStepStatus().equals(StepStatus.C);
    }

    @Override
    public StepTransLines doEvent(StepTransLines reqStepTransLines, StepTransLines dbstepTransLines, StepTrans stepTrans, Muser user) {
        StepTransLines objResponse;
        if (!(dbstepTransLines.getStage() == 2)) {
            throw new IllegalArgumentException("This step trans is not eligible for Complete");
        } else {
            dbstepTransLines.setStepStatus(StepStatus.C);
            dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());
            objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(user.getId()));//Changing Status.
            var childLine = this.stepTransLinesService.getChildStepLine(dbstepTransLines.getStepTransLinesId());
            if (childLine != null) {
                childLine.setStage(childLine.getStage() + 1);//current value should be 1(0->1). Eligible to be at wip now.
                //updating child
                this.stepTransLinesService.saveStepTransLines(childLine, false, Long.valueOf(user.getId()));
            } else {
                stepTrans.setStepTransStatus(StepTransStatus.C);
                this.stepTransRepository.save(stepTrans);
            }
        }
        return objResponse;
    }
}

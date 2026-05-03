package com.aye.backendservice.service.domain.stepTrans;


import com.aye.backendservice.service.StepTransLinesService;
import com.aye.entitylib.entity.user.Muser;
import com.aye.entitylib.entity.vehicleproject.StepSetupDetails;
import com.aye.entitylib.entity.vehicleproject.StepTrans;
import com.aye.entitylib.entity.vehicleproject.StepTransLines;
import com.aye.enums.StepStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 15:31
 */
@Component
@RequiredArgsConstructor
public class StepTransLineWipEvent implements StepTransLineEventStrategy {
    private final StepTransLinesService stepTransLinesService;

    @Override
    public boolean supports(StepTransLines reqStepTransLines) {
        return reqStepTransLines.getStepStatus().equals(StepStatus.W);
    }

    @Override
    public StepTransLines doEvent(StepTransLines reqStepTransLines, StepTransLines dbstepTransLines, StepTrans stepTrans, Muser user) {
        StepTransLines objResponse;
        if (!(dbstepTransLines.getStage() == 1)) {
            throw new IllegalArgumentException("This step trans is not eligible for WIP");
        } else {
            //Creating a new Step Trans and changing status
            if (!dbstepTransLines.getStepStatus().equals(StepStatus.H)) {
                dbstepTransLines.setStepStatus(StepStatus.W);
            }
            dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());
            //Fetching the setup

            List<StepSetupDetails> stepSetup = stepTrans.getStepSetup().getStepSetupDetails();
            var existingTrans = stepTrans.getStepTransLinesList();

            if (!(stepSetup.size() == existingTrans.size())) {
                var setupDetails = stepSetup.get(existingTrans.size());
                StepTransLines newStepTransLines = new StepTransLines();
                newStepTransLines.setStepStatus(StepStatus.N);
                newStepTransLines.setStepTrans(stepTrans);
                newStepTransLines.setStepSetupDetails(setupDetails);
                newStepTransLines.setParentLineId(dbstepTransLines.getStepTransLinesId());
                newStepTransLines.setStage(0);
                //creating new line
                this.stepTransLinesService.saveStepTransLines(newStepTransLines, true, Long.valueOf(user.getId()));
                //updating new line
                objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(user.getId()));
            } else {
                //There is no step left to create also no child left to increase its stage.
                dbstepTransLines.setStepStatus(reqStepTransLines.getStepStatus());
                dbstepTransLines.setStage(dbstepTransLines.getStage() + 1);
                objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(user.getId()));
            }
        }
        return objResponse;
    }
}

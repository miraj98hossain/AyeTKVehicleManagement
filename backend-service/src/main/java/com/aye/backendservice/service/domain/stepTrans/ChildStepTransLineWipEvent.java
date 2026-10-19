package com.aye.backendservice.service.domain.stepTrans;

import com.aye.backendservice.aspect.annotation.EnableLogging;
import com.aye.backendservice.service.StepTransDetailsService;
import com.aye.backendservice.service.StepTransLinesService;
import com.aye.entity.user.Muser;
import com.aye.entity.vehicleproject.StepSetupDetails;
import com.aye.entity.vehicleproject.StepTrans;
import com.aye.entity.vehicleproject.StepTransDetailsLines;
import com.aye.entity.vehicleproject.StepTransLines;
import com.aye.enums.StepStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Miraj
 * @date: 12/10/2026
 * @time: 10:33 am
 */
@Component
@RequiredArgsConstructor
public class ChildStepTransLineWipEvent implements StepTransLineEventStrategy {
    private final StepTransLinesService stepTransLinesService;
    private final StepTransDetailsService stepTransDetailsService;


    @Override
    public boolean supports(StepTransLines reqStepTransLines) {
        return reqStepTransLines.getParentTransId() != null && reqStepTransLines.getStepStatus().equals(StepStatus.W);
    }

    @EnableLogging
    @Override
    public StepTransLines doEvent(StepTransLines reqStepTransLines, StepTransLines dbstepTransLines, StepTrans stepTrans, Muser user) {
        StepTransLines objResponse;
        if (dbstepTransLines.getStepStatus().equals(StepStatus.W)) {
            throw new RuntimeException("This Step Trans " + dbstepTransLines.getStepTransLinesId() + " is already in progress");
        }
        if (!(dbstepTransLines.getStage() == 1)) {
            throw new RuntimeException("This step trans " + dbstepTransLines.getStepTransLinesId() + " is not eligible for WIP");
        } else {
            //Creating a new Step Trans and changing status
            dbstepTransLines.setStepStatus(StepStatus.W);
            dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());
            //Fetching the sub setup details
            List<StepSetupDetails> stepSetup = dbstepTransLines.getStepSetupDetails().getParentDetail().getStepSetupDetailsLines()
                    .stream()
                    .filter(stepSetupDetails -> stepSetupDetails.getIsActive() == 1).toList();
            List<StepTransDetailsLines> existingTrans = this.stepTransDetailsService.findByTrnRefId(dbstepTransLines.getStepTransLinesId()); //TODO ADD SELF REF IN STEP TRANS LIKE SETUP DTL
            Integer existingTransLength = existingTrans.get(0).getStage();
            if (!(stepSetup.size() == existingTransLength)) {
                var setupDetails = stepSetup.get(existingTransLength);
                StepTransLines newStepTransLines = new StepTransLines();
                newStepTransLines.setStepStatus(StepStatus.N);
                newStepTransLines.setStepTrans(stepTrans);
                newStepTransLines.setStepSetupDetails(setupDetails);
                newStepTransLines.setParentLineId(dbstepTransLines.getStepTransLinesId());
                newStepTransLines.setParentTransId(dbstepTransLines.getParentTransId());
                newStepTransLines.setSubTrnRefId(dbstepTransLines.getSubTrnRefId());
                newStepTransLines.setStage(0);
                //creating new line
                this.stepTransLinesService.saveStepTransLines(newStepTransLines, true, Long.valueOf(user.getId()));
                //updating new line
                objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(user.getId()));

                for (StepTransDetailsLines stepTransDetailsLines : existingTrans) {
                    stepTransDetailsLines.setStage(stepTransDetailsLines.getStage() + 1);
                    stepTransDetailsLines.setStepStatus(StepStatus.W);
                }
                this.stepTransDetailsService.saveAllLines(existingTrans);

            } else {
                //There is no step left to create also no child left to increase its stage.
                dbstepTransLines.setStepStatus(reqStepTransLines.getStepStatus());
                //dbstepTransLines.setStage(dbstepTransLines.getStage() + 1);
                objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(user.getId()));
            }

        }
        return objResponse;
    }
}

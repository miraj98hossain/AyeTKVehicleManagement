package com.aye.backendservice.service.domain.stepTrans;

import com.aye.backendservice.aspect.annotation.EnableLogging;
import com.aye.backendservice.service.CommonHelperService;
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
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: Miraj
 * @date: 12/10/2026
 * @time: 10:33 am
 */
@Component
@RequiredArgsConstructor
public class StepTransLineWipV2Event implements StepTransLineEventStrategy {
    private final StepTransLinesService stepTransLinesService;
    private final CommonHelperService commonHelperService;
    private final StepTransDetailsService stepTransDetailsService;

    @Override
    public boolean supports(StepTransLines reqStepTransLines) {
        return false;
        //return reqStepTransLines.getParentTransId() == null && reqStepTransLines.getStepStatus().equals(StepStatus.W);
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
            if (!reqStepTransLines.isHoldAble()) {
                //Creating a new Step Trans and changing status
                dbstepTransLines.setStepStatus(StepStatus.W);
                dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());
                //Fetching the setup
                List<StepSetupDetails> stepSetup = stepTrans.getStepSetup().getStepSetupDetails()
                        .stream()
                        .filter(stepSetupDetails -> stepSetupDetails.getIsActive() == 1).toList();
                var existingTrans = stepTrans.getStepTransLinesList();
                // removing sub trans from the existing list.
                existingTrans.removeIf(stepTransLines -> stepTransLines.getParentTransId() != null);
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
                    //dbstepTransLines.setStage(dbstepTransLines.getStage() + 1);
                    objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(user.getId()));
                }

            } else {
                /**
                 * setting hold by so that can remove from main transaction for a while
                 */
                dbstepTransLines.setHoldBy(user.getUserName());
                dbstepTransLines.setHasChildTrans(true);
                objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, false, Long.valueOf(user.getId()));
                List<StepTransDetailsLines> items = this.stepTransDetailsService.findByStepTransId(dbstepTransLines.getStepTrans().getStepTransId());
                List<Long> userItemAccess = this.commonHelperService.getUserAccessedItems(user.getUserName());
                items.removeIf(item -> !userItemAccess.contains(item.getInvItemId()));
                items.removeIf(item -> item.getStepStatus().equals(StepStatus.C));
                Map<Long, List<StepTransDetailsLines>> groupedByCategory = items.stream()
                        .collect(Collectors.groupingBy(StepTransDetailsLines::getInvItemId));

                for (Map.Entry<Long, List<StepTransDetailsLines>> entry : groupedByCategory.entrySet()) {
                    StepTransLines newStepTransLines = new StepTransLines();
                    newStepTransLines.setStepStatus(StepStatus.N);
                    newStepTransLines.setStepTrans(stepTrans);
                    newStepTransLines.setStepSetupDetails(entry.getValue().get(0).getStepSetup());
                    newStepTransLines.setParentLineId(0L);
                    newStepTransLines.setStage(1);
                    newStepTransLines.setParentTransId(dbstepTransLines.getStepTransLinesId());
                    newStepTransLines.setSubTrnRefId(entry.getValue().get(0).getStepTransDtlLnId());
                    //creating new line
                    var trnLRes = this.stepTransLinesService.saveStepTransLines(newStepTransLines, true, Long.valueOf(user.getId()));

                    for (StepTransDetailsLines item : entry.getValue()) {
                        item.setTrnRefId(trnLRes.getStepTransLinesId());
                        item.setStage(1);
                        item.setStepStatus(StepStatus.W);
                    }
                    this.stepTransDetailsService.saveAllLines(entry.getValue());
                }
            }

        }
        return objResponse;
    }
}

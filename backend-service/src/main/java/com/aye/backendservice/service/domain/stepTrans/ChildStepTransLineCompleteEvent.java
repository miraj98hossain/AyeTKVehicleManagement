package com.aye.backendservice.service.domain.stepTrans;


import com.aye.backendservice.aspect.annotation.EnableLogging;
import com.aye.backendservice.service.StepTransDetailsService;
import com.aye.backendservice.service.StepTransLinesService;
import com.aye.backendservice.service.StepTransService;
import com.aye.dto.request.StepTransLinesRequest;
import com.aye.entity.user.Muser;
import com.aye.entity.vehicleproject.StepTrans;
import com.aye.entity.vehicleproject.StepTransDetailsLines;
import com.aye.entity.vehicleproject.StepTransLines;
import com.aye.enums.StepStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 15:31
 */
@Service
@RequiredArgsConstructor
public class ChildStepTransLineCompleteEvent implements StepTransLineEventStrategy {
    private final StepTransLinesService stepTransLinesService;
    private final StepTransDetailsService stepTransDetailsService;
    @Autowired
    @Lazy
    private StepTransService stepTransService;

    @Override
    public boolean supports(StepTransLines reqStepTransLines) {
        return reqStepTransLines.getParentTransId() != null && reqStepTransLines.getStepStatus().equals(StepStatus.C);
    }

    @EnableLogging
    @Override
    public StepTransLines doEvent(StepTransLines reqStepTransLines, StepTransLines dbstepTransLines, StepTrans stepTrans, Muser user) {
        StepTransLines objResponse;
        if (dbstepTransLines.getStepStatus().equals(StepStatus.C)) {
            throw new RuntimeException("This Step Trans" + dbstepTransLines.getStepTransLinesId() + " is already Completed");
        }

        if (!(dbstepTransLines.getStage() == 1)) {
            throw new RuntimeException("This step trans" + dbstepTransLines.getStepTransLinesId() + " is not eligible for Complete");
        } else {
            dbstepTransLines.setStepStatus(StepStatus.C);
            dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());
            objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(user.getId()));//Changing Status.

            var childLine = this.stepTransLinesService.getChildStepLine(dbstepTransLines.getStepTransLinesId());
            if (childLine != null) {
                childLine.setStage(childLine.getStage() + 1);//current value should be 1(0->1). Eligible to be at wip now.
                //updating child
                this.stepTransLinesService.saveStepTransLines(childLine, false, Long.valueOf(user.getId()));

                List<StepTransDetailsLines> existingTrans = this.stepTransDetailsService.findByTrnRefId(dbstepTransLines.getStepTransLinesId());
                for (StepTransDetailsLines stepTransDetailsLines : existingTrans) {
                    stepTransDetailsLines.setTrnRefId(childLine.getStepTransLinesId());
                }
                this.stepTransDetailsService.saveAllLines(existingTrans);
            } else {
                List<StepTransDetailsLines> existingTrans = this.stepTransDetailsService.findByTrnRefId(dbstepTransLines.getStepTransLinesId());
                for (StepTransDetailsLines stepTransDetailsLines : existingTrans) {
                    stepTransDetailsLines.setStepStatus(StepStatus.C);
                }
                this.stepTransDetailsService.saveAllLines(existingTrans);

                List<StepTransDetailsLines> itemList = this.stepTransDetailsService.findByStepTransId(dbstepTransLines.getStepTrans().getStepTransId());

                boolean hasAnyNewItemLeft = itemList.stream()
                        .anyMatch(stepTransDetailsLines -> List.of(StepStatus.N, StepStatus.W).contains(stepTransDetailsLines.getStepStatus()));
                boolean canShowMainTrans = itemList.stream()
                        .anyMatch(stepTransDetailsLines -> stepTransDetailsLines.getStepStatus().equals(StepStatus.W));
                StepTransLines mainTransaction = this.stepTransLinesService.getStepTransLine(dbstepTransLines.getParentTransId());
                if (!hasAnyNewItemLeft) {
                    StepTransLinesRequest stepTransLinesRequest = new StepTransLinesRequest();
                    stepTransLinesRequest.setStepTransLinesId(mainTransaction.getStepTransLinesId());
                    stepTransLinesRequest.setStepStatus(StepStatus.W.getDisplayName());
                    stepTransLinesRequest.setHoldAble(false);
                    this.stepTransService.updateTransLines(stepTransLinesRequest, user.getUserName());
                    stepTransLinesRequest.setStepStatus(StepStatus.C.getDisplayName());
                    this.stepTransService.updateTransLines(stepTransLinesRequest, user.getUserName());
                }
                if (!canShowMainTrans) {
                    mainTransaction.setHoldBy(null);
                    this.stepTransLinesService.saveStepTransLines(mainTransaction, false, Long.valueOf(user.getId()));
                }
            }

        }
        return objResponse;
    }
}

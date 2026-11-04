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
        return reqStepTransLines.getParentTransId() != null
                && reqStepTransLines.getStepStatus().equals(StepStatus.C);
    }

    @EnableLogging
    @Override
    public StepTransLines doEvent(StepTransLines reqStepTransLines, StepTransLines dbStepTransLines,
                                  StepTrans stepTrans, Muser user) {
        Long lineId = dbStepTransLines.getStepTransLinesId();
        Long userId = Long.valueOf(user.getId());

        if (dbStepTransLines.getStepStatus().equals(StepStatus.C)) {
            throw new RuntimeException("This Step Trans" + lineId + " is already Completed");
        }
        if (dbStepTransLines.getStage() != 1) {
            throw new RuntimeException("This step trans" + lineId + " is not eligible for Complete");
        }

        dbStepTransLines.setStepStatus(StepStatus.C);
        dbStepTransLines.setRemarks(reqStepTransLines.getRemarks());
        StepTransLines objResponse = stepTransLinesService.saveStepTransLines(dbStepTransLines, true, userId); // Changing status.

        StepTransLines childLine = stepTransLinesService.getHierarchicalChildStepLine(lineId);
        if (childLine != null) {
            moveToChild(childLine, lineId, userId);
        } else {
            completeParentIfAllDone(dbStepTransLines, user);
            releaseHeldChildTransaction(dbStepTransLines, user);
        }
        return objResponse;
    }

    /**
     * Child becomes eligible for WIP (stage 0 -> 1) and takes over the existing detail lines.
     */
    private void moveToChild(StepTransLines childLine, Long lineId, Long userId) {
        childLine.setStage(childLine.getStage() + 1);
        stepTransLinesService.saveStepTransLines(childLine, false, userId);

        List<StepTransDetailsLines> existingTrans = stepTransDetailsService.findByTrnRefId(lineId);
        existingTrans.forEach(detail -> detail.setTrnRefId(childLine.getStepTransLinesId()));
        stepTransDetailsService.saveAllLines(existingTrans);
    }

    /**
     * Last step in the chain: complete the detail lines, and if nothing is left in N/W status,
     * complete the main transaction as well.
     */
    private void completeParentIfAllDone(StepTransLines dbStepTransLines, Muser user) {
        List<StepTransDetailsLines> existingTrans =
                stepTransDetailsService.findByTrnRefId(dbStepTransLines.getStepTransLinesId());
        existingTrans.forEach(detail -> detail.setStepStatus(StepStatus.C));
        stepTransDetailsService.saveAllLines(existingTrans);

        List<StepTransDetailsLines> itemList =
                stepTransDetailsService.findByStepTransId(dbStepTransLines.getStepTrans().getStepTransId());
        boolean hasAnyNewItemLeft = itemList.stream()
                .anyMatch(detail -> detail.getStepStatus() == StepStatus.N
                        || detail.getStepStatus() == StepStatus.W);

        StepTransLines mainTransaction = stepTransLinesService.getStepTransLine(dbStepTransLines.getParentTransId());
        if (!hasAnyNewItemLeft) {
            StepTransLinesRequest request = new StepTransLinesRequest();
            request.setStepTransLinesId(mainTransaction.getStepTransLinesId());
            request.setStepStatus(StepStatus.C.getDisplayName());
            stepTransService.updateTransLines(request, user.getUserName());
        }
    }

    private void releaseHeldChildTransaction(StepTransLines current, Muser user) {
        List<StepTransLines> allChildLines = this.stepTransLinesService.getChildStepLines(current.getParentTransId());
        allChildLines.removeIf(stepTransLines -> stepTransLines.getSubTrnRefId().equals(current.getSubTrnRefId()));
        allChildLines.removeIf(stepTransLines -> stepTransLines.getStepStatus().equals(StepStatus.C));
        for (StepTransLines stepTransLines : allChildLines) {
            stepTransLines.setHoldBy(null);
        }
        this.stepTransLinesService.saveAll(allChildLines);
    }
}

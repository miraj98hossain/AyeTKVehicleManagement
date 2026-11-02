package com.aye.backendservice.service.domain.stepTrans;


import com.aye.backendservice.aspect.annotation.EnableLogging;
import com.aye.backendservice.repository.StepTransRepository;
import com.aye.backendservice.service.StepTransLinesService;
import com.aye.entity.user.Muser;
import com.aye.entity.vehicleproject.StepTrans;
import com.aye.entity.vehicleproject.StepTransLines;
import com.aye.enums.StepStatus;
import com.aye.enums.StepTransStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 15:31
 */
@Component
@RequiredArgsConstructor
public class StepTransLineRejectEvent implements StepTransLineEventStrategy {
    private final StepTransRepository stepTransRepository;
    private final StepTransLinesService stepTransLinesService;

    @Override
    public boolean supports(StepTransLines reqStepTransLines) {
        return reqStepTransLines.getStepStatus().equals(StepStatus.R);
    }

    @EnableLogging
    @Override
    @Transactional
    public StepTransLines doEvent(StepTransLines reqStepTransLines, StepTransLines dbstepTransLines, StepTrans stepTrans, Muser user) {
        if (dbstepTransLines.getStepStatus().equals(StepStatus.R)) {
            throw new RuntimeException("This Step Trans" + dbstepTransLines.getStepTransLinesId() + " is already Rejected");
        }
        dbstepTransLines.setStepStatus(StepStatus.R);
        dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());
        if (dbstepTransLines.getParentTransId() == null) {
            rejectTransLine(dbstepTransLines, Long.valueOf(user.getId()), null);
        } else {
            StepTransLines stepTransLine = stepTransLinesService.getStepTransLine(dbstepTransLines.getParentTransId());
            stepTransLine.setStepStatus(StepStatus.R);
            stepTransLine.setRemarks(reqStepTransLines.getRemarks());
            rejectTransLine(stepTransLine, Long.valueOf(user.getId()), null);
        }


//        if (dbstepTransLines.getParentTransId() != null) {
//            StepTransLines stepTransLines = this.stepTransLinesService.getStepTransLine(dbstepTransLines.getParentTransId());
//            stepTransLines.setStepStatus(StepStatus.R);
//            stepTransLines.setRemarks(reqStepTransLines.getRemarks());
//            this.stepTransLinesService.saveStepTransLines(stepTransLines, true, Long.valueOf(user.getId()));
//        }
        stepTrans.setStepTransStatus(StepTransStatus.R);
        this.stepTransRepository.save(stepTrans);
        //objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(muser.getId()));//Changing Status.
        return dbstepTransLines;
    }


    protected void rejectTransLine(StepTransLines stepTransLines, Long currentUser, Set<Long> visited) {

        if (visited == null) {
            visited = new HashSet<>();
        }

        Long lineId = stepTransLines.getStepTransLinesId();
        if (visited.contains(lineId)) {
            return; // Already processed this step
        }
        visited.add(lineId);


        var hierarchicalChildStepLine = this.stepTransLinesService.getHierarchicalChildStepLine(lineId);
        if (hierarchicalChildStepLine != null) {
            rejectTransLine(hierarchicalChildStepLine, currentUser, visited);
        }
        for (StepTransLines subLine : stepTransLinesService.getChildStepLines(lineId)) {
            rejectTransLine(subLine, currentUser, visited);
        }

        // Reject current step
        stepTransLines.setStepStatus(StepStatus.R);
        this.stepTransLinesService.saveStepTransLines(stepTransLines, true, currentUser);
    }
}

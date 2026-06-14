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

    @Override
    @Transactional
    public StepTransLines doEvent(StepTransLines reqStepTransLines, StepTransLines dbstepTransLines, StepTrans stepTrans, Muser user) {
        if (dbstepTransLines.getStepStatus().equals(StepStatus.R)) {
            throw new RuntimeException("This Step Trans is already Rejected");
        }
        dbstepTransLines.setStepStatus(StepStatus.R);
        dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());
        rejectTransLine(dbstepTransLines, Long.valueOf(user.getId()), null);
        stepTrans.setStepTransStatus(StepTransStatus.R);
        this.stepTransRepository.save(stepTrans);
        //objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(muser.getId()));//Changing Status.
        return dbstepTransLines;
    }


    protected void rejectTransLine(StepTransLines stepTransLines, Long currentUser, Set<Long> visited) {

        if (visited == null) {
            visited = new HashSet<>();
        }

        Long stepId = stepTransLines.getStepTransLinesId();
        if (visited.contains(stepId)) {
            return; // Already processed this step
        }
        visited.add(stepId);


        var childLine = this.stepTransLinesService.getChildStepLine(stepId);
        if (childLine != null) {
            rejectTransLine(childLine, currentUser, visited);
        }

        // Reject current step
        stepTransLines.setStepStatus(StepStatus.R);
        this.stepTransLinesService.saveStepTransLines(stepTransLines, true, currentUser);
    }
}

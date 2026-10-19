package com.aye.backendservice.service.domain.stepTransDetails;


import com.aye.backendservice.aspect.annotation.EnableLogging;
import com.aye.backendservice.repository.StepTransDetailsLinesRepository;
import com.aye.backendservice.service.StepTransLinesService;
import com.aye.backendservice.service.StepTransService;
import com.aye.dto.request.StepTransLinesRequest;
import com.aye.entity.user.Muser;
import com.aye.entity.vehicleproject.StepTransDetails;
import com.aye.entity.vehicleproject.StepTransDetailsLines;
import com.aye.entity.vehicleproject.StepTransLines;
import com.aye.entity.vehicleproject.StepTransTimeline;
import com.aye.enums.StepStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 15:31
 */
@Component
@RequiredArgsConstructor
public class StepTransDetailsLinesWipEvent implements StepTransDetailsLinesEventStrategy {
    private final StepTransLinesService stepTransLinesService;
    private final StepTransDetailsLinesRepository lineRepo;
    @Lazy
    @Autowired
    private StepTransService stepTransService;

    @Override
    public boolean supports(StepStatus status) {
        return status.equals(StepStatus.W);
    }

    @EnableLogging
    @Override
    public StepTransDetailsLines doEvent(Long stepTransLineId, StepTransDetailsLines stepTransDetailsLines, StepTransTimeline stepTransTimeline, StepTransLinesRequest stepTransLinesRequest, Muser user) {
        StepTransLines stepTransLine = stepTransLinesService.getStepTransLine(stepTransLineId);
        stepTransTimeline.setStepTransLines(stepTransLine);
        stepTransDetailsLines.getStepTransTimeline().add(stepTransTimeline);
        List<StepTransDetails> stepTransDetails = stepTransLine.getStepTrans().getStepTransDetails();

        boolean areAllStageZero = stepTransDetails.stream()
                .flatMap(details -> details.getStepTransDetailsLines().stream())
                .allMatch(detailsLine -> detailsLine.getStage() == 0);
        stepTransDetailsLines.setStage(stepTransDetailsLines.getStage() + 1);
        stepTransDetailsLines.setStepStatus(StepStatus.W);
        var response = this.lineRepo.save(stepTransDetailsLines);

        if (areAllStageZero) {
            stepTransLinesRequest.setStepStatus(StepStatus.W.getDisplayName());
            this.stepTransService.updateTransLines(stepTransLinesRequest, user.getUserName());
        }
        return response;
    }
}

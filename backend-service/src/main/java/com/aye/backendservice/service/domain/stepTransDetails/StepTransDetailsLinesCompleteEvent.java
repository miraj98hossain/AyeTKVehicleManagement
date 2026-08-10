package com.aye.backendservice.service.domain.stepTransDetails;

import com.aye.backendservice.repository.StepTransDetailsLinesRepository;
import com.aye.backendservice.service.StepTransLinesService;
import com.aye.backendservice.service.StepTransService;
import com.aye.dtoLib.dto.request.StepTransLinesRequest;
import com.aye.entitylib.entity.user.Muser;
import com.aye.entitylib.entity.vehicleproject.StepTransDetails;
import com.aye.entitylib.entity.vehicleproject.StepTransDetailsLines;
import com.aye.entitylib.entity.vehicleproject.StepTransLines;
import com.aye.entitylib.entity.vehicleproject.StepTransTimeline;
import com.aye.enums.StepStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author: Miraj
 * @date: 10/08/2026
 * @time: 11:35 am
 */
@Component
@RequiredArgsConstructor
public class StepTransDetailsLinesCompleteEvent implements StepTransDetailsLinesEventStrategy {
    private final StepTransService stepTransService;
    private final StepTransLinesService stepTransLinesService;
    private final StepTransDetailsLinesRepository lineRepo;

    @Override
    public boolean supports(StepStatus status) {
        return status.equals(StepStatus.C);
    }

    @Override
    public StepTransDetailsLines doEvent(Long stepTransLineId,
                                         StepTransDetailsLines stepTransDetailsLines,
                                         StepTransTimeline stepTransTimeline, StepTransLinesRequest
                                                 stepTransLinesRequest, Muser user) {
        stepTransDetailsLines.setStage(stepTransDetailsLines.getStage() + 1);
        stepTransDetailsLines.setStepStatus(StepStatus.C);
        StepTransLines stepTransLine = stepTransLinesService.getStepTransLine(stepTransLineId);
        stepTransTimeline.setStepTransLines(stepTransLine);
        stepTransDetailsLines.getStepTransTimeline().add(stepTransTimeline);
        var response = this.lineRepo.save(stepTransDetailsLines);

        List<StepTransDetails> stepTransDetails = stepTransLine.getStepTrans().getStepTransDetails();
        boolean isAllOtherItemsLoaded = stepTransDetails.stream()
                .flatMap(details -> details.getStepTransDetailsLines().stream().filter(detailsLine -> !Objects.equals(detailsLine.getStepTransDtlLnId(), stepTransDetailsLines.getStepTransDetails().getStepTransDtlId())))
                .allMatch(detailsLine -> detailsLine.getStepStatus().equals(StepStatus.C));
        if (isAllOtherItemsLoaded) {
            stepTransLinesRequest.setStepStatus(StepStatus.C.getDisplayName());
            this.stepTransService.updateTransLines(stepTransLinesRequest, user.getUserName());

        }
        return response;
    }
}



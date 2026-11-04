package com.aye.backendservice.service.domain.stepTrans;

import com.aye.backendservice.aspect.annotation.EnableLogging;
import com.aye.backendservice.service.StepTransDetailsService;
import com.aye.backendservice.service.StepTransLinesService;
import com.aye.backendservice.service.StepTransScaleDetailsService;
import com.aye.dto.response.StepTransScaleDetailsDto;
import com.aye.entity.user.Muser;
import com.aye.entity.vehicleproject.*;
import com.aye.enums.StepStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
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
    private final StepTransScaleDetailsService stepTransScaleDetailsService;

    @Override
    public boolean supports(StepTransLines reqStepTransLines) {
        return reqStepTransLines.getParentTransId() != null
                && StepStatus.W.equals(reqStepTransLines.getStepStatus());
    }

    @EnableLogging
    @Override
    public StepTransLines doEvent(StepTransLines reqStepTransLines, StepTransLines dbStepTransLines,
                                  StepTrans stepTrans, Muser user) {
        Long stepTransLinesId = dbStepTransLines.getStepTransLinesId();
        Long currentUserId = Long.valueOf(user.getId());

        if (dbStepTransLines.getStepStatus().equals(StepStatus.W)) {
            throw new RuntimeException("This Step Trans " + stepTransLinesId + " is already in progress");
        }
        if (dbStepTransLines.getStage() != 1) {
            throw new RuntimeException("This step trans " + stepTransLinesId + " is not eligible for WIP");
        }

        dbStepTransLines.setStepStatus(StepStatus.W);
        dbStepTransLines.setRemarks(reqStepTransLines.getRemarks());

        List<StepSetupDetails> activeSubSetups = dbStepTransLines.getStepSetupDetails()
                .getParentDetail()
                .getStepSetupDetailsLines()
                .stream()
                .filter(setup -> setup.getIsActive() == 1)
                .toList();
        List<StepTransDetailsLines> existingTrans = stepTransDetailsService.findByTrnRefId(stepTransLinesId);
        int currentStage = existingTrans.get(0).getStage();

        // No step left to create and no child left to increase its stage.
        if (activeSubSetups.size() == currentStage) {
            dbStepTransLines.setStepStatus(reqStepTransLines.getStepStatus());
            return stepTransLinesService.saveStepTransLines(dbStepTransLines, true, currentUserId);
        }

        createNextLine(dbStepTransLines, activeSubSetups.get(currentStage), stepTrans, currentUserId);
        copyLastWeightIfRequired(dbStepTransLines, stepTrans, user);

        StepTransLines saved = stepTransLinesService.saveStepTransLines(dbStepTransLines, true, currentUserId);
        advanceChildStages(existingTrans);
        holdOtherChildTransaction(dbStepTransLines, user);
        return saved;
    }

    private void createNextLine(StepTransLines current, StepSetupDetails nextSetup,
                                StepTrans stepTrans, Long userId) {
        StepTransLines newLine = new StepTransLines();
        newLine.setStepStatus(StepStatus.N);
        newLine.setStepTrans(stepTrans);
        newLine.setStepSetupDetails(nextSetup);
        newLine.setParentLineId(current.getStepTransLinesId());
        newLine.setParentTransId(current.getParentTransId());
        newLine.setSubTrnRefId(current.getSubTrnRefId());
        newLine.setStage(0);
        stepTransLinesService.saveStepTransLines(newLine, true, userId);
    }

    private void copyLastWeightIfRequired(StepTransLines current, StepTrans stepTrans, Muser user) {
        StepSetupDetails setup = current.getStepSetupDetails();
        if (!setup.isFetchLastWeight()) {
            return;
        }

        List<StepTransScaleDetails> allScales = stepTrans.getStepTransScaleDetails();
        allScales.sort(Comparator.comparingLong(scale -> scale.getStepTransLines().getStepTransLinesId()));
        StepTransScaleDetails lastScale = allScales.get(allScales.size() - 1);

        StepTransScaleDetailsDto dto = new StepTransScaleDetailsDto();
        dto.setStepTransId(stepTrans.getStepTransId());
        dto.setStepTransLineId(current.getStepTransLinesId());
        dto.setStepSetupDetailId(setup.getStepSetupDetailsId());
        dto.setStepTransDetailsLineId(current.getSubTrnRefId());
        dto.setScaleName(lastScale.getScaleName());
        dto.setWeight(lastScale.getWeight());
        dto.setUom(lastScale.getUom());
        stepTransScaleDetailsService.save(dto, user.getUserName());
    }

    private void advanceChildStages(List<StepTransDetailsLines> existingTrans) {
        existingTrans.forEach(line -> {
            line.setStage(line.getStage() + 1);
            line.setStepStatus(StepStatus.W);
        });
        stepTransDetailsService.saveAllLines(existingTrans);
    }

    private void holdOtherChildTransaction(StepTransLines current, Muser user) {
        List<StepTransLines> allChildLines = this.stepTransLinesService.getChildStepLines(current.getParentTransId());
        allChildLines.removeIf(stepTransLines -> stepTransLines.getSubTrnRefId().equals(current.getSubTrnRefId()));
        allChildLines.removeIf(stepTransLines -> stepTransLines.getStepStatus().equals(StepStatus.C));
        for (StepTransLines stepTransLines : allChildLines) {
            stepTransLines.setHoldBy(user.getUserName());
        }
        this.stepTransLinesService.saveAll(allChildLines);
    }
}

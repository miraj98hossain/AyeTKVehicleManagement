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
public class ChildStepTransLineWipEventCopy implements StepTransLineEventStrategy {
    private final StepTransLinesService stepTransLinesService;
    private final StepTransDetailsService stepTransDetailsService;
    private final StepTransScaleDetailsService stepTransScaleDetailsService;

    @Override
    public boolean supports(StepTransLines reqStepTransLines) {
        return false;
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
            List<StepTransDetailsLines> existingTrans = this.stepTransDetailsService.findByTrnRefId(dbstepTransLines.getStepTransLinesId());
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
                StepSetupDetails stepSetupDetails = dbstepTransLines.getStepSetupDetails();
                if (stepSetupDetails.getParentDetail() != null) {
                    boolean isFetchLastWeight = stepSetupDetails.isFetchLastWeight();
                    if (isFetchLastWeight) {
                        List<StepTransScaleDetails> allScales = stepTrans.getStepTransScaleDetails();
                        allScales.sort(Comparator.comparingLong(value -> value.getStepTransLines().getStepTransLinesId()));
                        StepTransScaleDetails stepTransScaleDetails = allScales.get(allScales.size() - 1);
                        StepTransScaleDetailsDto scaleDetailsDto = new StepTransScaleDetailsDto();
                        scaleDetailsDto.setStepTransId(stepTrans.getStepTransId());
                        scaleDetailsDto.setStepTransLineId(dbstepTransLines.getStepTransLinesId());
                        scaleDetailsDto.setStepSetupDetailId(dbstepTransLines.getStepSetupDetails().getStepSetupDetailsId());
                        scaleDetailsDto.setStepTransDetailsLineId(dbstepTransLines.getSubTrnRefId());
                        scaleDetailsDto.setScaleName(stepTransScaleDetails.getScaleName());
                        scaleDetailsDto.setWeight(stepTransScaleDetails.getWeight());
                        scaleDetailsDto.setUom(stepTransScaleDetails.getUom());
                        this.stepTransScaleDetailsService.save(scaleDetailsDto, user.getUserName());
                    }
                }
                //updating current line
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

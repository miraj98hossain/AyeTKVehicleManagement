package com.aye.backendservice.service;

import com.aye.dtoLib.dto.response.StepTransScaleDetailsDto;
import com.aye.entitylib.entity.vehicleproject.embeddable.StepTransScaleDetailsId;
import com.aye.mapper.StepTransScaleDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 06/05/2026
 * @time: 4:44 pm
 */
@Service
public class StepTransScaleDetailsViewService {
    @Autowired
    private StepTransScaleDetailsService service;
    @Autowired
    private StepTransScaleDetailsMapper mapper;


    public StepTransScaleDetailsDto save(StepTransScaleDetailsDto stepTransScaleDetailsDto, String currentUser) {

        return this.mapper.toResponseDto(this.service.save(stepTransScaleDetailsDto, currentUser));
    }


    public StepTransScaleDetailsDto findById(Long stepTransId,
                                             Long stepTransLinesId,
                                             Long stepSetupDetailsId) {
        StepTransScaleDetailsId stepTransScaleDetailsId = new StepTransScaleDetailsId();
        stepTransScaleDetailsId.setStepTransId(stepTransId);
        stepTransScaleDetailsId.setStepTransLinesId(stepTransLinesId);
        stepTransScaleDetailsId.setStepSetupDetailsId(stepSetupDetailsId);
        return this.mapper.toResponseDto(this.service.findById(stepTransScaleDetailsId));
    }

}

package com.aye.backendservice.service;

import com.aye.dtoLib.dto.response.StepTransScaleDetailsDto;
import com.aye.entitylib.entity.vehicleproject.StepTransScaleDetails;
import com.aye.entitylib.entity.vehicleproject.embeddable.StepTransScaleDetailsId;

/**
 * @author: Miraj
 * @date: 06/05/2026
 * @time: 4:44 pm
 */

public interface StepTransScaleDetailsService {
    StepTransScaleDetails save(StepTransScaleDetailsDto stepTransScaleDetailsDto, String currentUser);

    StepTransScaleDetails findById(StepTransScaleDetailsId stepTransScaleDetailsId);
}

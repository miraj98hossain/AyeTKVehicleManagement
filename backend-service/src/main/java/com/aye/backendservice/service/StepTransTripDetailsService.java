package com.aye.backendservice.service;


import com.aye.dto.response.StepTransTripDetailsDto;
import com.aye.entity.vehicleproject.StepTransTripDetails;
import com.aye.entity.vehicleproject.embeddable.StepTransTripDetailsId;

/**
 * @author: Miraj
 * @date: 06/05/2026
 * @time: 4:44 pm
 */

public interface StepTransTripDetailsService {
    StepTransTripDetails save(StepTransTripDetailsDto stepTransTripDetailsDto, String currentUser);

    StepTransTripDetails findById(StepTransTripDetailsId stepTransTripDetailsId);
}

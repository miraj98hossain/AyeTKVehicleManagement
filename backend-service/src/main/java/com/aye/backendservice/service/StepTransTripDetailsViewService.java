package com.aye.backendservice.service;


import com.aye.dto.response.StepTransTripDetailsDto;
import com.aye.entity.vehicleproject.embeddable.StepTransTripDetailsId;
import com.aye.mapper.StepTransTripDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 06/05/2026
 * @time: 4:44 pm
 */
@Service
public class StepTransTripDetailsViewService {
    @Autowired
    private StepTransTripDetailsService service;
    @Autowired
    private StepTransTripDetailsMapper mapper;


    public StepTransTripDetailsDto save(StepTransTripDetailsDto stepTransTripDetailsDto, String currentUser) {

        return this.mapper.toResponseDto(this.service.save(stepTransTripDetailsDto, currentUser));
    }


    public StepTransTripDetailsDto findById(Long stepTransId, Long stepTransLinesId) {
        StepTransTripDetailsId stepTransTripDetailsId = new StepTransTripDetailsId();
        stepTransTripDetailsId.setStepTransId(stepTransId);
        stepTransTripDetailsId.setStepTransLinesId(stepTransLinesId);
        return this.mapper.toResponseDto(this.service.findById(stepTransTripDetailsId));
    }

}

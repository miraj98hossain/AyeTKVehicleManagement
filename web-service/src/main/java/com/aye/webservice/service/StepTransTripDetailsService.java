package com.aye.webservice.service;

import com.aye.dto.response.StepTransTripDetailsDto;
import com.aye.webservice.feignclient.StepTransTripDetailsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StepTransTripDetailsService {
    @Autowired
    private StepTransTripDetailsFeignClient feignClient;


    public StepTransTripDetailsDto save(StepTransTripDetailsDto stepTransTripDetailsDto, String currentUser) {

        return this.feignClient.save(stepTransTripDetailsDto, currentUser).getBody();
    }


    public StepTransTripDetailsDto findById(Long stepTransId, Long stepTransLinesId) {
        return this.feignClient.findById(stepTransId, stepTransLinesId).getBody();
    }

}
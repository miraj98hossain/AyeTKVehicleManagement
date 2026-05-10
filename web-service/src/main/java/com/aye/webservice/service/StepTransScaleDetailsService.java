package com.aye.webservice.service;

import com.aye.dtoLib.dto.response.StepTransScaleDetailsDto;
import com.aye.webservice.feignclient.StepTransScaleDetailsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StepTransScaleDetailsService {
    @Autowired
    private StepTransScaleDetailsFeignClient feignClient;


    public StepTransScaleDetailsDto save(StepTransScaleDetailsDto stepTransScaleDetailsDto, String currentUser) {

        return this.feignClient.save(stepTransScaleDetailsDto, currentUser).getBody();
    }


    public StepTransScaleDetailsDto findById(Long stepTransId,
                                             Long stepTransLinesId,
                                             Long stepSetupDetailsId) {
        return this.feignClient.findById(stepTransId, stepTransLinesId, stepSetupDetailsId).getBody();
    }

}
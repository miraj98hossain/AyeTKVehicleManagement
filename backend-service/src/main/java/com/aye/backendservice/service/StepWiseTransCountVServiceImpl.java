package com.aye.backendservice.service;


import com.aye.backendservice.repository.StepWiseTransCountVRepo;
import com.aye.entitylib.entity.vehicleproject.StepWiseTransCountV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StepWiseTransCountVServiceImpl implements StepWiseTransCountVService {

    @Autowired
    private StepWiseTransCountVRepo stepWiseTransCountVRepo;


    @Override
    public List<StepWiseTransCountV> getCountByDetailId(List<Long> detailId) {
        List<StepWiseTransCountV> list = this.stepWiseTransCountVRepo.findAllByStepSetupDetailsIdIn(detailId);
        return list;
    }
}

package com.aye.backendservice.DDD.domain;

import com.aye.RestfulServer.model.Muser;
import com.aye.backendservice.DDD.factory.StepTransDetailsFactory;
import com.aye.backendservice.entity.StepTransDetails;
import com.aye.backendservice.repository.StepTransDetailsRepository;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 15:31
 */
@Component
public class GeneralStepDetailsCreationStrategy implements StepTransCreationStrategy {

    @Autowired
    private StepTransDetailsRepository repository;
    @Autowired
    private StepTransDetailsFactory factory;


    @Override
    public boolean supports(StepTransDetailsRequest req) {
        return req.getOrderNumber() == null && (req.getScheduleNo() == null);
    }

    @Override
    public List<StepTransDetails> create(StepTransDetailsRequest req, Muser user) {
        List<StepTransDetails> list = new ArrayList<>();
        var obj = repository.save(factory.fromStepDetailsReq(req, user));
        list.add(obj);
        return list;
    }
}

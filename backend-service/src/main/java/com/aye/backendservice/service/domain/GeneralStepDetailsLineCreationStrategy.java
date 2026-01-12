package com.aye.backendservice.service.domain;

import com.aye.RestfulServer.model.Muser;
import com.aye.backendservice.entity.StepTransDetailsLines;
import com.aye.backendservice.repository.StepTransDetailsLinesRepository;
import com.aye.backendservice.service.factory.StepTransDetailsFactory;
import com.aye.commonlib.dto.request.StepTransDetailsLinesRequest;
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
public class GeneralStepDetailsLineCreationStrategy implements StepTransDetailsLineCreationStrategy {

    @Autowired
    private StepTransDetailsLinesRepository repository;
    @Autowired
    private StepTransDetailsFactory factory;


    @Override
    public List<StepTransDetailsLines> create(StepTransDetailsLinesRequest req, Muser user) {
        List<StepTransDetailsLines> list = new ArrayList<>();
        var obj = repository.save(factory.fromStepDetailsLineReq(req, user));
        list.add(obj);
        return list;
    }
}

package com.aye.backendservice.service.domain.stepTransDetails;


import com.aye.backendservice.repository.StepTransDetailsLinesRepository;
import com.aye.backendservice.service.factory.StepTransDetailsFactory;
import com.aye.dtoLib.dto.request.StepTransDetailsLinesRequest;
import com.aye.entitylib.entity.user.Muser;
import com.aye.entitylib.entity.vehicleproject.StepTransDetailsLines;
import jakarta.persistence.EntityNotFoundException;
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
        var dbObj = repository.findById(req.getStepTransDtlLnId()).orElseThrow(() -> new EntityNotFoundException("StepTransDetailsLines not found with id: " + req.getStepTransDtlLnId()));
        var obj = repository.save(factory.fromStepDetailsLineReq(req, dbObj, user));
        list.add(obj);
        return list;
    }
}

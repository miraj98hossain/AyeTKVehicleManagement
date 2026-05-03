package com.aye.backendservice.service;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 13:50
 */


import com.aye.RestfulServer.service.MuserService;
import com.aye.backendservice.service.domain.StepTransDetailsCreationStrategy;
import com.aye.backendservice.service.domain.StepTransDetailsLineCreationStrategy;
import com.aye.dtoLib.dto.request.StepTransDetailsLinesRequest;
import com.aye.dtoLib.dto.request.StepTransDetailsRequest;
import com.aye.entitylib.entity.user.Muser;
import com.aye.entitylib.entity.vehicleproject.StepTransDetails;
import com.aye.entitylib.entity.vehicleproject.StepTransDetailsLines;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class StepTransDetailsCreationService {
    @Autowired
    private List<StepTransDetailsCreationStrategy> stepTransDetailsCreationStrategies;
    @Autowired
    private List<StepTransDetailsLineCreationStrategy> stepTransDetailsLineCreationStrategy;
    @Autowired
    private MuserService muserService;
    @Autowired
    private StepTransService stepTransService;

    public List<StepTransDetails> save(StepTransDetailsRequest req, String username) {
        Muser user = muserService.findByUserName(username);
        List<StepTransDetails> result = new ArrayList<>();
        for (StepTransDetailsCreationStrategy strategy : stepTransDetailsCreationStrategies) {
            if (strategy.supports(req)) {

                result = strategy.create(req, user);
                break;

            }
        }
//        // fallback single creation
//        StepTransDetails single = stepTransDetailsCreationStrategies.stream()
//                .filter(s -> s.supports(req))
//                .findFirst().
//                orElseThrow(() -> new IllegalStateException("No strategy found"))
//                .create(req, user).get(0);
        return result;
    }

    public List<StepTransDetailsLines> saveLine(StepTransDetailsLinesRequest req, String username) {
        Muser user = muserService.findByUserName(username);

        for (StepTransDetailsLineCreationStrategy strategy : stepTransDetailsLineCreationStrategy) {
            List<StepTransDetailsLines> result = strategy.create(req, user);
            return result;
        }
        throw new IllegalStateException("No line strategy found");
    }

}


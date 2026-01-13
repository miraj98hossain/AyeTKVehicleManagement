package com.aye.backendservice.service;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 13:50
 */


import com.aye.RestfulServer.service.MuserService;
import com.aye.backendservice.mapper.StepTransDetailsLinesMapper;
import com.aye.backendservice.mapper.StepTransDetailsMapper;
import com.aye.backendservice.service.domain.StepTransDetailsCreationStrategy;
import com.aye.backendservice.service.domain.StepTransDetailsLineCreationStrategy;
import com.aye.backendservice.utils.ApiResponseFactory;
import com.aye.commonlib.dto.request.StepTransDetailsLinesRequest;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.entitylib.entity.Muser;
import com.aye.entitylib.entity.StepTransDetails;
import com.aye.entitylib.entity.StepTransDetailsLines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StepTransDetailsCreationService {
    @Autowired
    private List<StepTransDetailsCreationStrategy> stepTransDetailsCreationStrategies;
    @Autowired
    private List<StepTransDetailsLineCreationStrategy> stepTransDetailsLineCreationStrategy;

    @Autowired
    private MuserService muserService;
    @Autowired
    private StepTransDetailsMapper mapper;
    @Autowired
    private StepTransDetailsLinesMapper linesMapper;


    public ApiRequestResponse save(StepTransDetailsRequest req, String username) {
        Muser user = muserService.findByUserName(username);

        for (StepTransDetailsCreationStrategy strategy : stepTransDetailsCreationStrategies) {
            if (strategy.supports(req)) {
                List<StepTransDetails> result = strategy.create(req, user);
                return ApiResponseFactory.success(
                        result.stream().map(mapper::toResponseDto).toList()
                );
            }
        }

        // fallback single creation
        StepTransDetails single = stepTransDetailsCreationStrategies.stream()
                .filter(s -> s.supports(req))
                .findFirst().
                orElseThrow(() -> new IllegalStateException("No strategy found"))
                .create(req, user).get(0);

        return ApiResponseFactory.success(mapper.toResponseDto(single));
    }

    public ApiRequestResponse saveLine(StepTransDetailsLinesRequest req, String username) {
        Muser user = muserService.findByUserName(username);

        for (StepTransDetailsLineCreationStrategy strategy : stepTransDetailsLineCreationStrategy) {
            List<StepTransDetailsLines> result = strategy.create(req, user);
            return ApiResponseFactory.success(
                    result.stream().map(linesMapper::toResponseDto).toList()
            );
        }
        throw new IllegalStateException("No line strategy found");
    }

}


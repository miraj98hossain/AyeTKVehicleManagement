package com.aye.backendservice.DDD.service;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 13:50
 */


import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.service.MuserService;
import com.aye.backendservice.DDD.domain.StepTransCreationStrategy;
import com.aye.backendservice.entity.StepTransDetails;
import com.aye.backendservice.mapper.StepTransDetailsMapper;
import com.aye.backendservice.utils.ApiResponseFactory;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StepTransDetailsAppService {

    private final List<StepTransCreationStrategy> strategies;
    private final MuserService muserService;
    private final StepTransDetailsMapper mapper;

    public StepTransDetailsAppService(List<StepTransCreationStrategy> strategies,
                                      MuserService muserService,
                                      StepTransDetailsMapper mapper) {
        this.strategies = strategies;
        this.muserService = muserService;
        this.mapper = mapper;
    }

    public ApiRequestResponse save(StepTransDetailsRequest req, String username) {
        Muser user = muserService.findByUserName(username);

        for (StepTransCreationStrategy strategy : strategies) {
            if (strategy.supports(req)) {
                List<StepTransDetails> result = strategy.create(req, user);
                return ApiResponseFactory.success(
                        result.stream().map(mapper::toResponseDto).toList()
                );
            }
        }

        // fallback single creation
        StepTransDetails single = strategies.stream()
                .filter(s -> s.supports(req))
                .findFirst().
                orElseThrow(() -> new IllegalStateException("No strategy found"))
                .create(req, user).get(0);

        return ApiResponseFactory.success(mapper.toResponseDto(single));
    }
}


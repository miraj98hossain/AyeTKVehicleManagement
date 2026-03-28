package com.aye.backendservice.service;


import com.aye.backendservice.repository.StepWiseTransCountVRepo;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;
import com.aye.dtoLib.dto.response.StepWiseTransCountVResponse;
import com.aye.mapper.StepWiseTransCountVMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StepWiseTransCountVServiceImpl implements StepWiseTransCountVService {

    @Autowired
    private StepWiseTransCountVRepo stepWiseTransCountVRepo;
    @Autowired
    private StepWiseTransCountVMapper stepWiseTransCountVMapper;

    @Override
    public ApiRequestResponse getCountByDetailId(List<Long> detailId) {

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "stepWiseTransCount",
                StepWiseTransCountVResponse.class.getName(),
                this.stepWiseTransCountVRepo.findAllByStepSetupDetailsIdIn(detailId)
                        .stream().map(stepWiseTransCountVMapper::toResponseDto).toList()
        );
    }
}

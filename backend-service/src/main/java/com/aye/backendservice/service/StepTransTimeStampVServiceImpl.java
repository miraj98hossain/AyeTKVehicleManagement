package com.aye.backendservice.service;

import com.aye.backendservice.mapper.StepTransTimeStampVMapper;
import com.aye.backendservice.repository.StepTransTimeStampVRepo;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.StepTransTimeStampVResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 01/01/2026
 * @time: 13:39
 * @project: AyeTKVehicleManagement
 */
@Service
public class StepTransTimeStampVServiceImpl implements StepTransTimeStampVService {
    @Autowired
    private StepTransTimeStampVRepo stepTransTimeStampVRepo;
    @Autowired
    private StepTransTimeStampVMapper stepTransTimeStampVMapper;

    @Override
    public ApiRequestResponse getTimeStampByDetailsId(Long stepSetupDetailsId) {
        var list = stepTransTimeStampVRepo.findAllByStepSetupDetailsId(stepSetupDetailsId).stream()
                .map(this.stepTransTimeStampVMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "strTimeStampResList",
                StepTransTimeStampVResponse.class.getName(),
                list
        );
    }
}

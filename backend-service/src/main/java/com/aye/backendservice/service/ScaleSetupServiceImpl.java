package com.aye.backendservice.service;

import com.aye.backendservice.mapper.ScaleSetupMapper;
import com.aye.backendservice.repository.ScaleSetupRepo;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.ScaleSetupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 16:04
 */
@Service
public class ScaleSetupServiceImpl implements ScaleSetupService {
    @Autowired
    private ScaleSetupRepo scaleSetupRepo;
    @Autowired
    private ScaleSetupMapper scaleSetupMapper;

    @Override
    public ApiRequestResponse findAllScaleSetup() {
        var list = scaleSetupRepo.findAll().stream().map(scaleSetupMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "ipList",
                ScaleSetupResponse.class.getName(),
                list
        );
    }

    @Override
    public ApiRequestResponse filterScaleSetup(Long orgId) {
        var list = scaleSetupRepo.findAllByOrgHierarchy_Id(orgId)
                .stream().map(scaleSetupMapper::toResponseDtoWOIP).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "ipList",
                ScaleSetupResponse.class.getName(),
                list
        );
    }
}

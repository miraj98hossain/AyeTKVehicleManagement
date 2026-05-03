package com.aye.backendservice.service;


import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;
import com.aye.dtoLib.dto.response.ScaleSetupResponse;
import com.aye.mapper.ScaleSetupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 16:04
 */
@Service
public class ScaleSetupViewService {
    @Autowired
    private ScaleSetupService scaleSetupService;
    @Autowired
    private ScaleSetupMapper scaleSetupMapper;


    public ApiRequestResponse findAllScaleSetup() {
        var list = scaleSetupService.findAllScaleSetup();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "ipList",
                ScaleSetupResponse.class.getName(),
                list.stream().map(scaleSetupMapper::toResponseDto)
        );
    }


    public ApiRequestResponse filterScaleSetup(Long orgId) {
        var list = scaleSetupService.filterScaleSetup(orgId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "ipList",
                ScaleSetupResponse.class.getName(),
                list.stream().map(scaleSetupMapper::toResponseDtoWOIP)
        );
    }
}

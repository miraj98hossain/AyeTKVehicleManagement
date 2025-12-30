package com.aye.backendservice.service;

import com.aye.RestfulServer.model.common.AppModuleCode;
import com.aye.RestfulServer.model.om.AppModule;
import com.aye.RestfulServer.service.AppModuleService;
import com.aye.backendservice.mapper.AppModuleMapper;
import com.aye.commonlib.dto.request.AppModuleRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.AppModuleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppModuleBServiceImpl implements AppModuleBService {
    @Autowired
    private AppModuleService appModuleService;
    @Autowired
    private AppModuleMapper appModuleMapper;

    @Override
    public ApiRequestResponse findByCode(String appModuleCode) {
        AppModule appModule = this.appModuleService.findByCode(AppModuleCode.valueOf(appModuleCode));
        AppModuleResponse appModuleResponse = appModuleMapper.toResponseDto(appModule);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "appModule",
                AppModuleResponse.class.getName(), appModuleResponse
        );

    }

    @Override
    public ApiRequestResponse findAll() {
        List<AppModuleResponse> list = this.appModuleService.findall()
                .stream().map(appModuleMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "modules",
                AppModuleResponse.class.getName(), list
        );
    }

    @Override
    public ApiRequestResponse findById(Long id) {
        AppModule appModule = this.appModuleService.findById(id);
        AppModuleResponse appModuleResponse = appModuleMapper.toResponseDto(appModule);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "module",
                AppModuleResponse.class.getName(), appModuleResponse
        );
    }

    @Override
    public ApiRequestResponse saveAppmodule(AppModuleRequest appModuleRequest) {
        AppModule appModule = appModuleMapper.dtoToEntity(appModuleRequest);
        this.appModuleService.saveAppmodule(appModule);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                null, null,
                null, null
        );
    }
}

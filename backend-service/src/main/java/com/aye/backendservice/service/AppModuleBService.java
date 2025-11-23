package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.AppModuleRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface AppModuleBService {

    ApiRequestResponse findByCode(String appModuleCode);

    ApiRequestResponse findAll();

    ApiRequestResponse findById(Long id);

    ApiRequestResponse saveAppmodule(AppModuleRequest appModuleRequest);
}

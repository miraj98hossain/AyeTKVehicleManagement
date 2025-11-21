package com.aye.backendservice.service;

import com.aye.RestfulServer.model.common.AppModuleCode;
import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface AppModuleBService {

    ApiRequestResponse findByCode(AppModuleCode appModuleCode);

    ApiRequestResponse findAll();
}

package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface AppModuleBService {

    ApiRequestResponse findByCode(String appModuleCode);

    ApiRequestResponse findAll();
}

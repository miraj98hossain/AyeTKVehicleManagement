package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.AppModuleRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;

import java.util.List;

public interface AppModuleBService {

    ApiRequestResponse findByCode(String appModuleCode);

    ApiRequestResponse findAll();

    ApiRequestResponse findById(Long id);

    ApiRequestResponse findByIds(List<Long> ids);

    ApiRequestResponse saveAppmodule(AppModuleRequest appModuleRequest);
}

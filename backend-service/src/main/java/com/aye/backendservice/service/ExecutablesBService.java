package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.ExecutableParameterRequest;
import com.aye.commonlib.dto.request.ExecutablesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface ExecutablesBService {

    ApiRequestResponse save(ExecutablesRequest executablesRequest);

    ApiRequestResponse saveParameters(ExecutableParameterRequest parameterRequest);

    ApiRequestResponse findById(Long executablesId);

    ApiRequestResponse findByFileName(String fileName);

    ApiRequestResponse findAll();

    ApiRequestResponse findByExecName(String execName, Long moduleCode);

    ApiRequestResponse findByModuleCode(String execName, Long moduleCode);

    ApiRequestResponse findParametersByExecutable(Long executableId, Integer temltId);

    ApiRequestResponse findParamById(Long exeParmId);
}

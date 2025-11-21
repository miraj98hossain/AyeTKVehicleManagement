package com.aye.backendservice.service;

import com.aye.RestfulServer.model.ExecutablesSearch;
import com.aye.commonlib.dto.request.ExecutableParameterRequest;
import com.aye.commonlib.dto.request.ExecutablesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface ExecutablesBService {

    ApiRequestResponse save(ExecutablesRequest executablesRequest);

    ApiRequestResponse saveParameters(ExecutableParameterRequest parameterRequest);

    ApiRequestResponse findById(Long executablesId);

    ApiRequestResponse findByFileName(String fileName);

    ApiRequestResponse findAll();

    ApiRequestResponse findByExecName(ExecutablesSearch search);

    ApiRequestResponse findByModuleCode(ExecutablesSearch search);

    ApiRequestResponse findParametersByExecutable(Long executableId, Integer temltId);

    ApiRequestResponse findParamById(Long exeParmId);
}

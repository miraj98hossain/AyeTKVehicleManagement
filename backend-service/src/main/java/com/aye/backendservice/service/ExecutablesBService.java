package com.aye.backendservice.service;


import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.concurrency.ExecutableParameterDto;
import com.aye.dto.response.concurrency.ExecutablesDto;

public interface ExecutablesBService {

    ApiRequestResponse save(ExecutablesDto executablesRequest);

    ApiRequestResponse saveParameters(ExecutableParameterDto parameterRequest);

    ApiRequestResponse findById(Long executablesId);

    ApiRequestResponse findByFileName(String fileName);

    ApiRequestResponse findAll();

    ApiRequestResponse findByExecName(String execName, Long moduleCode);

    ApiRequestResponse findByModuleCode(String execName, Long moduleCode);

    ApiRequestResponse findParametersByExecutable(Long executableId, Integer temltId);

    ApiRequestResponse findParamById(Long exeParmId);
}

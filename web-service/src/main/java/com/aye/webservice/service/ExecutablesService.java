package com.aye.webservice.service;


import com.aye.commonlib.dto.request.ExecutableParameterRequest;
import com.aye.commonlib.dto.request.ExecutablesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.ExecutablesFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ExecutablesService {
    @Autowired
    ExecutablesFeignClient executablesFeignClient;

    public ApiRequestResponse save(@RequestBody ExecutablesRequest executablesRequest) {
        return this.executablesFeignClient.save(executablesRequest).getBody();
    }

    public ApiRequestResponse saveParameters(@RequestBody ExecutableParameterRequest parameterRequest) {
        return this.executablesFeignClient.saveParameters(parameterRequest).getBody();
    }

    public ApiRequestResponse findById(@PathVariable("executablesId") Long executablesId) {
        return this.executablesFeignClient.findById(executablesId).getBody();
    }

    public ApiRequestResponse findByFileName(@RequestParam("fileName") String fileName) {
        return this.executablesFeignClient.findByFileName(fileName).getBody();
    }

    public ApiRequestResponse findAll() {
        return this.executablesFeignClient.findAll().getBody();
    }

    public ApiRequestResponse findByExecName(@RequestParam("execName") String execName,
                                             @RequestParam("moduleCode") Long moduleCode) {
        return this.executablesFeignClient.findByExecName(execName, moduleCode).getBody();
    }

    public ApiRequestResponse findByModuleCode(@RequestParam("execName") String execName,
                                               @RequestParam("moduleCode") Long moduleCode) {
        return this.executablesFeignClient.findByModuleCode(execName, moduleCode).getBody();
    }

    public ApiRequestResponse findParametersByExecutable(@PathVariable("executableId") Long executableId,
                                                         @PathVariable("temltId") Integer temltId) {
        return this.executablesFeignClient.findParametersByExecutable(executableId, temltId).getBody();
    }

    public ApiRequestResponse findParamById(@PathVariable("exeParmId") Long exeParmId) {
        return this.executablesFeignClient.findParamById(exeParmId).getBody();
    }
}

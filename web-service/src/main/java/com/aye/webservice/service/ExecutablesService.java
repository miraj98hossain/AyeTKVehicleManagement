package com.aye.webservice.service;


import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.concurrency.ExecutableParameterDto;
import com.aye.dto.response.concurrency.ExecutablesDto;
import com.aye.webservice.feignclient.ExecutablesFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ExecutablesService {
    @Autowired
    private ExecutablesFeignClient executablesFeignClient;

    // ******************** Executables ************************


    public ApiRequestResponse getExecutableParamList(Long executableId) {
        return this.executablesFeignClient.getExecutableParamList(executableId).getBody();
    }

    public ApiRequestResponse findAllExecutables() {
        return executablesFeignClient.findAllExecutables().getBody();
    }

    public ApiRequestResponse saveExecutables(ExecutablesDto executablesMDTO, String userName) {
        return this.executablesFeignClient.saveExecutables(executablesMDTO, userName).getBody();
    }

    public ApiRequestResponse findExecutablesById(Long id) {
        return this.executablesFeignClient.findExecutablesById(id).getBody();
    }

    // ******************** Executable Params ************************

    public ApiRequestResponse uploadFile(Long executableId, MultipartFile files, String userName) throws IOException {
        return this.executablesFeignClient.uploadFile(executableId, files, userName).getBody();
    }

    public ApiRequestResponse getExecutableParamById(Long executableId, Long exectParamId) {
        return this.executablesFeignClient.getExecutableParamById(executableId, exectParamId).getBody();
    }

    public ApiRequestResponse saveExecutableParam(ExecutableParameterDto executableParameterDto, Long executableId, String userName) {
        return this.executablesFeignClient.saveExecutableParam(executableParameterDto, executableId, userName).getBody();
    }
}

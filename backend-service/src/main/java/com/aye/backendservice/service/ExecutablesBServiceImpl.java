package com.aye.backendservice.service;

import com.aye.RestfulServer.model.DataSet;
import com.aye.RestfulServer.model.ExecutableParameter;
import com.aye.RestfulServer.model.Executables;
import com.aye.RestfulServer.model.ExecutablesSearch;
import com.aye.RestfulServer.service.DataSetService;
import com.aye.RestfulServer.service.ExecutableParameterService;
import com.aye.RestfulServer.service.ExecutablesService;
import com.aye.backendservice.mapper.ExecutableMapper;
import com.aye.backendservice.mapper.ExecutableParameterMapper;
import com.aye.commonlib.dto.request.ExecutableParameterRequest;
import com.aye.commonlib.dto.request.ExecutablesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.ExecutableParameterResponse;
import com.aye.commonlib.dto.response.ExecutablesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecutablesBServiceImpl implements ExecutablesBService {
    @Autowired
    ExecutableParameterService executableParameterService;
    @Autowired
    ExecutablesService executablesService;
    @Autowired
    DataSetService dataSetService;
    @Autowired
    ExecutableMapper executableMapper;
    @Autowired
    ExecutableParameterMapper executableParameterMapper;

    @Override
    public ApiRequestResponse save(ExecutablesRequest executablesRequest) {
        Executables executables = executableMapper.dtoToExecutables(executablesRequest);
        this.executablesService.save(executables);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                null, null,
                null, null
        );
    }

    @Override
    public ApiRequestResponse saveParameters(ExecutableParameterRequest parameterRequest) {
        Executables executables = this.executablesService.findById(parameterRequest.getExecutableId());
        DataSet dataSet = this.dataSetService.findById(parameterRequest.getDataSetId());
        ExecutableParameter exeParam = executableParameterMapper.dtoToEntity(parameterRequest);
        exeParam.setExecutables(executables);
        exeParam.setDataSet(dataSet);
        this.executableParameterService.save(exeParam);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                null, null,
                null, null
        );
    }

    @Override
    public ApiRequestResponse findById(Long executablesId) {
        Executables executables = this.executablesService.findById(executablesId);
        ExecutablesResponse executablesResponse = executableMapper.toResponseDto(executables);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "exectblcrt",
                ExecutablesResponse.class.getName(), executablesResponse
        );
    }

    @Override
    public ApiRequestResponse findByFileName(String fileName) {
        Executables executables = this.executablesService.findByFileName(fileName);
        ExecutablesResponse executablesResponse = executableMapper.toResponseDto(executables);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "exectblcrt",
                ExecutablesResponse.class.getName(), executablesResponse
        );
    }

    @Override
    public ApiRequestResponse findAll() {
        List<ExecutablesResponse> executablesList = this.executablesService.findAll()
                .stream().map(executableMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "exetbl",
                ExecutablesResponse.class.getName(), executablesList
        );
    }

    @Override
    public ApiRequestResponse findByExecName(String execName, Long moduleCode) {
        ExecutablesSearch search = new ExecutablesSearch();
        search.setModuleCode(moduleCode);
        search.setExecName(execName);
        List<ExecutablesResponse> executablesList = this.executablesService.findByExecName(search)
                .stream().map(executableMapper::toResponseDto).toList();

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "exetbl",
                ExecutablesResponse.class.getName(), executablesList
        );
    }

    @Override
    public ApiRequestResponse findByModuleCode(String execName, Long moduleCode) {
        ExecutablesSearch search = new ExecutablesSearch();
        search.setModuleCode(moduleCode);
        search.setExecName(execName);
        List<ExecutablesResponse> executablesList = this.executablesService.findByModuleCode(search)
                .stream().map(executableMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "exetbl",
                ExecutablesResponse.class.getName(), executablesList
        );
    }

    @Override
    public ApiRequestResponse findParametersByExecutable(Long executable, Integer temltId) {
        List<ExecutableParameterResponse> list = this.executableParameterService
                .findByExecutables(executablesService.findById(executable), temltId)
                .stream().map(executableParameterMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "getAllParam",
                ExecutableParameterResponse.class.getName(), list
        );
    }

    @Override
    public ApiRequestResponse findParamById(Long exeParmId) {
        ExecutableParameterResponse execParamRes = executableParameterMapper
                .toResponseDto(this.executableParameterService.findById(exeParmId));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "executableParameter",
                ExecutableParameterResponse.class.getName(), execParamRes
        );
    }
}

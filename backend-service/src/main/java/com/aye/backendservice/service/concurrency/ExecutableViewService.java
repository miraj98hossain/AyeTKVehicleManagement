package com.aye.backendservice.service.concurrency;

import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.ApiRequestResponseDetail;
import com.aye.dto.response.concurrency.ExecutableParameterDto;
import com.aye.dto.response.concurrency.ExecutablesDto;
import com.aye.entity.concurrency.Executables;
import com.aye.mapper.concurrency.ExecutableMapper;
import com.aye.mapper.concurrency.ExecutableParameterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author: Miraj
 * @date: 11/08/2026
 * @time: 6:00 pm
 */
@Service
public class ExecutableViewService {
    @Autowired
    private ExecutableService executableService;
    @Autowired
    private ExecutableMapper executableMapper;
    @Autowired
    private ExecutableParameterMapper executableParameterMapper;

    public ApiRequestResponse findAllExecutables() {
        List<Executables> list = this.executableService.findAllExecutables();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "executableList",
                ExecutablesDto.class.getName(), executableMapper.toResponseDto(list)
        );
    }


    public ApiRequestResponse findById(Long id) {
        ExecutablesDto executables = executableMapper.toResponseDto(executableService.findById(id));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "executable",
                ExecutablesDto.class.getName(), executables
        );
    }


    public ApiRequestResponse saveExecutable(ExecutablesDto executablesDto, String userName) {
        ExecutablesDto executables = executableMapper.toResponseDto(executableService.saveExecutable(executablesDto, userName));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "executable",
                ExecutablesDto.class.getName(), executables
        );
    }


    public ApiRequestResponse findExecutablesById(Long executablesId) {
        ExecutablesDto executables = executableMapper.toResponseDto(executableService.findExecutablesById(executablesId));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "executable",
                ExecutablesDto.class.getName(), executables
        );
    }


    public ApiRequestResponse uploadFile(Long executableId, MultipartFile files, String userName) throws IOException {

        ExecutablesDto executables = executableMapper.toResponseDto(executableService.uploadFile(executableId, files, userName));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "executable",
                ExecutablesDto.class.getName(), executables
        );
    }


    // ******************** Executable Params ************************

    public ApiRequestResponse getExecutableParamList(Long executableId) {

        List<ExecutableParameterDto> executableParameters = this.executableParameterMapper.toResponseDto(this.executableService.getExecutableParamList(executableId));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "executableParameterList",
                ExecutableParameterDto.class.getName(), executableParameters
        );

    }


    public ApiRequestResponse getExecutableParamById(Long executableId, Long exectParamId) {
        ExecutableParameterDto executableParameters = this.executableParameterMapper
                .toResponseDto(this.executableService.getExecutableParamById(executableId, exectParamId));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "executableParameter",
                ExecutableParameterDto.class.getName(), executableParameters
        );
    }


    public ApiRequestResponse saveExecutableParam(ExecutableParameterDto executableParameterDto, Long executableId, String userName) {

        ExecutableParameterDto executableParameters = this.executableParameterMapper
                .toResponseDto(this.executableService.saveExecutableParam(executableParameterDto, executableId, userName));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "executableParameter",
                ExecutableParameterDto.class.getName(), executableParameters
        );

    }


}

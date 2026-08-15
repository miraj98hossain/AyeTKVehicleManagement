package com.aye.backendservice.service.concurrency;

import com.aye.dto.response.concurrency.ExecutableParameterDto;
import com.aye.dto.response.concurrency.ExecutablesDto;
import com.aye.entity.concurrency.ExecutableParameter;
import com.aye.entity.concurrency.Executables;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExecutableService {

    List<Executables> findAllExecutables();

    Executables findById(Long id);

    Executables saveExecutable(ExecutablesDto executablesMDTO, String userName);

    Executables findExecutablesById(Long executablesId);

    Executables uploadFile(Long executableId, MultipartFile files, String userName) throws IOException;


    // ******************** Executable Params ************************
    List<ExecutableParameter> getExecutableParamList(Long executableId);

    ExecutableParameter getExecutableParamById(Long executableId, Long exectParamId);

    ExecutableParameter saveExecutableParam(ExecutableParameterDto executableParameterDto, Long executableId, String userName);
}

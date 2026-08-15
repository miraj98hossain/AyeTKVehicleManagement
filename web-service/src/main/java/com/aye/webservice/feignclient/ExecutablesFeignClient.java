package com.aye.webservice.feignclient;


import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.concurrency.ExecutableParameterDto;
import com.aye.dto.response.concurrency.ExecutablesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "ExecutablesFeignClient", url = "${backend.service.url}/api/executables")
public interface ExecutablesFeignClient {

    // ******************** Executables ************************

    @GetMapping("/findAllExecutables")
    ResponseEntity<ApiRequestResponse> findAllExecutables();

    @PostMapping("/saveExecutables/{userName}")
    ResponseEntity<ApiRequestResponse> saveExecutables(@RequestBody ExecutablesDto executablesMDTO, @PathVariable String userName);

    @GetMapping("/executable/{id}")
    ResponseEntity<ApiRequestResponse> findExecutablesById(@PathVariable Long id);

    @PostMapping(value = "/uploadFile/{userName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ApiRequestResponse> uploadFile(@RequestParam Long executableId,
                                                  @RequestPart("files") MultipartFile files,
                                                  @PathVariable("userName") String userName) throws IOException;

    // ******************** Executable Params ************************

    @GetMapping("/getExecutableParamList/{executableId}")
    ResponseEntity<ApiRequestResponse> getExecutableParamList(@PathVariable Long executableId);

    @GetMapping("/getExecutableParamById/{executableId}/{exectParamId}")
    ResponseEntity<ApiRequestResponse> getExecutableParamById(@PathVariable("executableId") Long executableId,
                                                              @PathVariable("exectParamId") Long exectParamId);

    @PostMapping("/saveExecutableParam/{executableId}/{userName}")
    ResponseEntity<ApiRequestResponse> saveExecutableParam(@RequestBody ExecutableParameterDto executableParameterDto,
                                                           @PathVariable("executableId") Long executableId,
                                                           @PathVariable("userName") String userName);
}

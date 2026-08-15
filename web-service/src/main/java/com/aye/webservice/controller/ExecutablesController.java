package com.aye.webservice.controller;


import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.concurrency.ExecutableParameterDto;
import com.aye.dto.response.concurrency.ExecutablesDto;
import com.aye.webservice.service.ExecutablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/executables")
public class ExecutablesController {

    @Autowired
    private ExecutablesService executablesService;

    // ******************** Executables ************************

    @GetMapping("/findAllExecutables")
    ResponseEntity<ApiRequestResponse> findAllExecutables() {
        return ResponseEntity.ok(this.executablesService.findAllExecutables());
    }

    @PostMapping("/saveExecutables/{userName}")
    ResponseEntity<ApiRequestResponse> saveExecutables(@RequestBody ExecutablesDto executablesMDTO, @PathVariable String userName) {
        return ResponseEntity.ok(this.executablesService.saveExecutables(executablesMDTO, userName));
    }

    @GetMapping("/executable/{id}")
    ResponseEntity<ApiRequestResponse> findExecutablesById(@PathVariable Long id) {
        return ResponseEntity.ok(this.executablesService.findExecutablesById(id));
    }

    @PostMapping(value = "/uploadFile/{userName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ApiRequestResponse> uploadFile(@RequestParam Long executableId,
                                                  @RequestPart("files") MultipartFile files,
                                                  @PathVariable("userName") String userName) throws IOException {
        return ResponseEntity.ok(this.executablesService.uploadFile(executableId, files, userName));
    }

    // ******************** Executable Params ************************

    @GetMapping("/getExecutableParamList/{executableId}")
    ResponseEntity<ApiRequestResponse> getExecutableParamList(@PathVariable Long executableId) {
        return ResponseEntity.ok(this.executablesService.getExecutableParamList(executableId));
    }

    @GetMapping("/getExecutableParamById/{executableId}/{exectParamId}")
    ResponseEntity<ApiRequestResponse> getExecutableParamById(@PathVariable("executableId") Long executableId, @PathVariable("exectParamId") Long exectParamId) {
        return ResponseEntity.ok(this.executablesService.getExecutableParamById(executableId, exectParamId));
    }

    @PostMapping("/saveExecutableParam/{executableId}/{userName}")
    ResponseEntity<ApiRequestResponse> saveExecutableParam(@RequestBody ExecutableParameterDto executableParameterDto, @PathVariable("executableId") Long executableId, @PathVariable("userName") String userName) {
        return ResponseEntity.ok(this.executablesService.saveExecutableParam(executableParameterDto, executableId, userName));
    }
}

package com.aye.webservice.controller;

import com.aye.commonlib.dto.request.ExecutableParameterRequest;
import com.aye.commonlib.dto.request.ExecutablesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.ExecutablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/executables")
public class ExecutablesController {

    @Autowired
    private ExecutablesService executablesService;

    @PostMapping("/save")
    ResponseEntity<ApiRequestResponse> save(@RequestBody ExecutablesRequest executablesRequest) {
        return ResponseEntity.ok(executablesService.save(executablesRequest));
    }

    @PostMapping("/saveParameters")
    ResponseEntity<ApiRequestResponse> saveParameters(@RequestBody ExecutableParameterRequest parameterRequest) {
        return ResponseEntity.ok(executablesService.saveParameters(parameterRequest));
    }

    @GetMapping("/findById/{executablesId}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("executablesId") Long executablesId) {
        return ResponseEntity.ok(executablesService.findById(executablesId));
    }

    @GetMapping("/findByFileName")
    ResponseEntity<ApiRequestResponse> findByFileName(@RequestParam("fileName") String fileName) {
        return ResponseEntity.ok(executablesService.findByFileName(fileName));
    }

    @GetMapping("/findAll")
    ResponseEntity<ApiRequestResponse> findAll() {
        return ResponseEntity.ok(executablesService.findAll());
    }

    @GetMapping("/findByExecName")
    ResponseEntity<ApiRequestResponse> findByExecName(@RequestParam("execName") String execName, @RequestParam("moduleCode") Long moduleCode) {
        return ResponseEntity.ok(executablesService.findByExecName(execName, moduleCode));
    }

    @GetMapping("/findByModuleCode")
    ResponseEntity<ApiRequestResponse> findByModuleCode(@RequestParam("execName") String execName, @RequestParam("moduleCode") Long moduleCode) {
        return ResponseEntity.ok(executablesService.findByModuleCode(execName, moduleCode));
    }

    @GetMapping("/findParametersByExecutable/{executableId}/{temltId}")
    ResponseEntity<ApiRequestResponse> findParametersByExecutable(@PathVariable("executableId") Long executableId,
                                                                  @PathVariable("temltId") Integer temltId) {
        return ResponseEntity.ok(executablesService.findParametersByExecutable(executableId, temltId));
    }

    @GetMapping("/findParamById/{exeParmId}")
    ResponseEntity<ApiRequestResponse> findParamById(@PathVariable("exeParmId") Long exeParmId) {
        return ResponseEntity.ok(executablesService.findParamById(exeParmId));
    }
}

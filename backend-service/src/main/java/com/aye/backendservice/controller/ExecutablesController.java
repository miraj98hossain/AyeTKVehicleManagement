package com.aye.backendservice.controller;

import com.aye.backendservice.service.ExecutablesBService;
import com.aye.commonlib.dto.request.ExecutableParameterRequest;
import com.aye.commonlib.dto.request.ExecutablesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/executables")
public class ExecutablesController {

    @Autowired
    private ExecutablesBService executablesBService;

    @PostMapping("/save")
    ResponseEntity<ApiRequestResponse> save(@RequestBody ExecutablesRequest executablesRequest) {
        return ResponseEntity.ok(executablesBService.save(executablesRequest));
    }

    @PostMapping("/saveParameters")
    ResponseEntity<ApiRequestResponse> saveParameters(@RequestBody ExecutableParameterRequest parameterRequest) {
        return ResponseEntity.ok(executablesBService.saveParameters(parameterRequest));
    }

    @GetMapping("/findById/{executablesId}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("executablesId") Long executablesId) {
        return ResponseEntity.ok(executablesBService.findById(executablesId));
    }

    @GetMapping("/findByFileName")
    ResponseEntity<ApiRequestResponse> findByFileName(@RequestParam("fileName") String fileName) {
        return ResponseEntity.ok(executablesBService.findByFileName(fileName));
    }

    @GetMapping("/findAll")
    ResponseEntity<ApiRequestResponse> findAll() {
        return ResponseEntity.ok(executablesBService.findAll());
    }

    @GetMapping("/findByExecName")
    ResponseEntity<ApiRequestResponse> findByExecName(@RequestParam("execName") String execName, @RequestParam("moduleCode") Long moduleCode) {
        return ResponseEntity.ok(executablesBService.findByExecName(execName, moduleCode));
    }

    @GetMapping("/findByModuleCode")
    ResponseEntity<ApiRequestResponse> findByModuleCode(@RequestParam("execName") String execName, @RequestParam("moduleCode") Long moduleCode) {
        return ResponseEntity.ok(executablesBService.findByModuleCode(execName, moduleCode));
    }

    @GetMapping("/findParametersByExecutable/{executableId}/{temltId}")
    ResponseEntity<ApiRequestResponse> findParametersByExecutable(@PathVariable("executableId") Long executableId,
                                                                  @PathVariable("temltId") Integer temltId) {
        return ResponseEntity.ok(executablesBService.findParametersByExecutable(executableId, temltId));
    }

    @GetMapping("/findParamById/{exeParmId}")
    ResponseEntity<ApiRequestResponse> findParamById(@PathVariable("exeParmId") Long exeParmId) {
        return ResponseEntity.ok(executablesBService.findParamById(exeParmId));
    }
}

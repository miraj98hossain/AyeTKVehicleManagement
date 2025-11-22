package com.aye.webservice.feignclient;


import com.aye.commonlib.dto.request.ExecutableParameterRequest;
import com.aye.commonlib.dto.request.ExecutablesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ExecutablesFeignClient",
        url = "${backend.service.url}/api/executables")
public interface ExecutablesFeignClient {

    @PostMapping("/save")
    ResponseEntity<ApiRequestResponse> save(@RequestBody ExecutablesRequest executablesRequest);

    @PostMapping("/saveParameters")
    ResponseEntity<ApiRequestResponse> saveParameters(@RequestBody ExecutableParameterRequest parameterRequest);

    @GetMapping("/findById/{executablesId}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("executablesId") Long executablesId);

    @GetMapping("/findByFileName")
    ResponseEntity<ApiRequestResponse> findByFileName(@RequestParam("fileName") String fileName);

    @GetMapping("/findAll")
    ResponseEntity<ApiRequestResponse> findAll();

    @GetMapping("/findByExecName")
    ResponseEntity<ApiRequestResponse> findByExecName(@RequestParam("execName") String execName,
                                                      @RequestParam("moduleCode") Long moduleCode);

    @GetMapping("/findByModuleCode")
    ResponseEntity<ApiRequestResponse> findByModuleCode(@RequestParam("execName") String execName,
                                                        @RequestParam("moduleCode") Long moduleCode);

    @GetMapping("/findParametersByExecutable/{executableId}/{temltId}")
    ResponseEntity<ApiRequestResponse> findParametersByExecutable(@PathVariable("executableId") Long executableId,
                                                                  @PathVariable("temltId") Integer temltId);

    @GetMapping("/findParamById/{exeParmId}")
    ResponseEntity<ApiRequestResponse> findParamById(@PathVariable("exeParmId") Long exeParmId);
}

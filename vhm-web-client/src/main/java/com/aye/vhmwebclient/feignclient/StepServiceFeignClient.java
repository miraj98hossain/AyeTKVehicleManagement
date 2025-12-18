package com.aye.vhmwebclient.feignclient;


import com.aye.commonlib.dto.request.StepRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "StepServiceFeignClient",
        url = "${web.service.url}/api/steps")
public interface StepServiceFeignClient {
    @PostMapping("/save")
    ResponseEntity<ApiRequestResponse> saveStep(
            @RequestParam String currentUserName,
            @RequestBody StepRequest stepRequest);

    @PutMapping("/save")
    ResponseEntity<ApiRequestResponse> updateStep(
            @RequestParam String currentUserName,
            @RequestBody StepRequest stepRequest);

    @GetMapping("/{id}")
    ResponseEntity<ApiRequestResponse> getStep(@PathVariable("id") Long id);

    @GetMapping()
    ResponseEntity<ApiRequestResponse> getSteps(
            @RequestParam(required = false) String searchWords,
            Pageable pageable);

}

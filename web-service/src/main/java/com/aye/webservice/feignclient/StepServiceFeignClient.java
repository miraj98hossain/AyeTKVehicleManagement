package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.request.StepRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "StepServiceFeignClient",
        url = "${backend.service.url}${backend.service.steps.prefix}")
public interface StepServiceFeignClient {
    @PostMapping("/save")
    ResponseEntity<ApiRequestResponse> saveStep(
            @RequestBody StepRequest stepRequest,
            @RequestParam Long currentUserId);

    @PutMapping("/save")
    ResponseEntity<ApiRequestResponse> updateStep(
            @RequestBody StepRequest stepRequest,
            @RequestParam Long currentUserId
    );

    @GetMapping("/{id}")
    ResponseEntity<ApiRequestResponse> getStep(@PathVariable("id") Long id);

    @GetMapping()
    ResponseEntity<ApiRequestResponse> getAllSteps(@RequestParam(required = false) String searchWords, Pageable pageable);

}

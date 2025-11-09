package com.aye.vhmwebclient.feignclient;


import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "StepServiceFeignClient", url = "${web.service.url}${web.service.steps.prefix}")
public interface StepServiceFeignClient {
    @PostMapping("/save")
    ResponseEntity<ApiRequestResponse> saveStep(
            @RequestParam Long currentUserId,
            @RequestBody StepRequest stepRequest);

    @PutMapping("/save")
    ResponseEntity<ApiRequestResponse> updateStep(
            @RequestParam Long currentUserId,
            @RequestBody StepRequest stepRequest);

    @GetMapping("/{id}")
    ResponseEntity<ApiRequestResponse> getStep(@PathVariable("id") Long id);

    @GetMapping()
    ResponseEntity<ApiRequestResponse> getAllSteps(@SpringQueryMap Pageable pageable);

}

package com.mhdev.webclient.feignclient;


import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.StepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "StepServiceFeignClient", url = "${web.service.url}${web.service.steps.prefix}")
public interface StepServiceFeignClient {
    @PostMapping("/save")
    ResponseEntity<StepResponse> saveStep(@RequestBody StepRequest stepRequest);

    @PutMapping("/save")
    ResponseEntity<StepResponse> updateStep(@RequestBody StepRequest stepRequest);

    @GetMapping("/{id}")
    ResponseEntity<StepResponse> getStep(@PathVariable("id") Long id);

    @GetMapping()
    ResponseEntity<Page<StepResponse>> getAllSteps(Pageable pageable);

}

package com.mhdev.webservice.feignclient;

import com.mhdev.commonlib.dto.request.StepRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "StepServiceFeignClient", url = "${backend.service.url}${backend.service.steps.prefix}")
public interface StepServiceFeignClient {
    @PostMapping("/save")
    ResponseEntity<Response> saveStep(@RequestBody StepRequest stepRequest);

    @PutMapping("/save")
    ResponseEntity<Response> updateStep(@RequestBody StepRequest stepRequest);

    @GetMapping("/{id}")
    ResponseEntity<Response> getStep(@PathVariable("id") Long id);

    @GetMapping()
    ResponseEntity<Response> getAllSteps(Pageable pageable);

}

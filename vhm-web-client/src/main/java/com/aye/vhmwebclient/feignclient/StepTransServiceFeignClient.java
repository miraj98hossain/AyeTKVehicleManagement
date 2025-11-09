package com.aye.vhmwebclient.feignclient;


import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "StepTransServiceFeignClient", url = "${web.service.url}${web.service.step.trans.prefix}")
public interface StepTransServiceFeignClient {


    @PostMapping("/create")
    ResponseEntity<ApiRequestResponse> create(
            @RequestParam Long currentUserId,
            @RequestBody StepTransRequest stepTransRequest);

    @PostMapping("/update-lines")
    ResponseEntity<ApiRequestResponse> updateLines(
            @RequestParam Long currentUserId,
            @RequestBody StepTransLinesRequest stepTransLinesRequest);

    @GetMapping
    ResponseEntity<ApiRequestResponse> findAll(@SpringQueryMap Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id);

    @GetMapping("/findAllBySetupDtls")
    ResponseEntity<ApiRequestResponse> findAllBySetupDtls(@RequestParam List<Long> setupDetailIds, Pageable pageable);
}

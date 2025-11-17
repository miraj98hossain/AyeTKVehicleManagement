package com.aye.mobileservice.feignclient;


import com.aye.commonlib.dto.request.StepTransLinesRequest;
import com.aye.commonlib.dto.request.StepTransRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "StepTransServiceFeignClient",
        url = "${backend.service.url}${backend.service.step.trans.prefix}")
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
    ResponseEntity<ApiRequestResponse> findAll(Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id);

    @GetMapping("/findAllBySetupDtls")
    ResponseEntity<ApiRequestResponse> findAllBySetupDtls(@RequestParam
                                                          List<Long> setupDetailIds,
                                                          @RequestParam(required = false) String searchWords,
                                                          @PageableDefault(size = 10, page = 0) Pageable pageable);
}

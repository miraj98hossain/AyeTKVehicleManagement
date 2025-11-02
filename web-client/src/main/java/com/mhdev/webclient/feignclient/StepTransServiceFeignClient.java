package com.mhdev.webclient.feignclient;


import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "StepTransServiceFeignClient", url = "${web.service.url}${web.service.step.trans.prefix}")
public interface StepTransServiceFeignClient {


    @PostMapping("/create")
    ResponseEntity<ApiRequestResponse> create(
            @RequestBody StepTransRequest stepTransRequest);

    @PostMapping("/update-lines")
    ResponseEntity<ApiRequestResponse> updateLines(@RequestBody StepTransLinesRequest stepTransLinesRequest);

    @GetMapping
    ResponseEntity<ApiRequestResponse> findAll(@SpringQueryMap Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id);
}

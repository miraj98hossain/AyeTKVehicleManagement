package com.mhdev.webservice.feignclient;


import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.StepTransLinesResponse;
import com.mhdev.commonlib.dto.response.StepTransResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "StepTransServiceFeignClient", url = "${backend.service.url}${backend.service.step.trans.prefix}")
public interface StepTransServiceFeignClient {


    @PostMapping("/create")
    StepTransResponse create(
            @RequestBody StepTransRequest stepTransRequest);

    @PostMapping("/update-lines")
    StepTransLinesResponse updateLines(@RequestBody StepTransLinesRequest stepTransLinesRequest);

    @GetMapping
    Page<StepTransResponse> findAll(Pageable pageable);

    @GetMapping("/{id}")
    StepTransResponse findById(@PathVariable("id") Long id);
}

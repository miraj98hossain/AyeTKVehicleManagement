package com.aye.mobileservice.feignclient;


import com.aye.commonlib.dto.request.StepTransLinesRequest;
import com.aye.commonlib.dto.request.StepTransRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.validationGroup.StepTransCreateValidation;
import com.aye.commonlib.dto.validationGroup.StepTransLinesUpdateValidation;
import jakarta.validation.groups.Default;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "StepTransServiceFeignClient",
        url = "${backend.service.url}/api/step-trans")
public interface StepTransServiceFeignClient {


    @PostMapping("/create")
    ResponseEntity<ApiRequestResponse> create(
            @RequestParam String userName,
            @Validated({StepTransCreateValidation.class, Default.class})
            @RequestBody StepTransRequest stepTransRequest
    );

    @PostMapping("/update-lines")
    ResponseEntity<ApiRequestResponse> updateLines(
            @RequestParam String userName,
            @Validated({StepTransLinesUpdateValidation.class, Default.class})
            @RequestBody StepTransLinesRequest stepTransLinesRequest);

    @GetMapping
    ResponseEntity<ApiRequestResponse> findAll(Pageable pageable);

    @GetMapping("/findAllByTempDtlId")
    ResponseEntity<ApiRequestResponse> findAllByTempDtlId(@RequestParam Integer tempDtlId,
                                                          @RequestParam(required = false) String searchWords,
                                                          @PageableDefault(size = 10, page = 0) Pageable pageable);


    @GetMapping("/{id}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id);

}

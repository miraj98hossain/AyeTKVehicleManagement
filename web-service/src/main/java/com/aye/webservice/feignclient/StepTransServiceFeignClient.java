package com.aye.webservice.feignclient;


import com.aye.dto.request.StepTransFilter;
import com.aye.dto.request.StepTransLinesRequest;
import com.aye.dto.request.StepTransRequest;
import com.aye.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "StepTransServiceFeignClient",
        url = "${backend.service.url}/api/step-trans")
public interface StepTransServiceFeignClient {


    @PostMapping("/create")
    ResponseEntity<ApiRequestResponse> create(
            @RequestParam String userName,
            @RequestBody StepTransRequest stepTransRequest);

    @PostMapping("/update-lines")
    ResponseEntity<ApiRequestResponse> updateLines(
            @RequestParam String userName,
            @RequestBody StepTransLinesRequest stepTransLinesRequest);

    @GetMapping
    ResponseEntity<ApiRequestResponse> findAll(Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id);

    @GetMapping("/findAllByTempDtlId")
    ResponseEntity<ApiRequestResponse> findAllByTempDtlId(@RequestParam Integer tempDtlId,
                                                          @RequestParam Long invOrgId,
                                                          @RequestParam String userName,
                                                          @RequestParam(required = false) String searchWords,
                                                          @RequestParam(required = false) String filterStatus,
                                                          @PageableDefault(size = 10, page = 0) Pageable pageable);

    @PostMapping("/searchTransactions")
    ResponseEntity<ApiRequestResponse> searchTransactions(@RequestParam Integer tempDtlId,
                                                          @RequestBody StepTransFilter stepTransFilter);

}

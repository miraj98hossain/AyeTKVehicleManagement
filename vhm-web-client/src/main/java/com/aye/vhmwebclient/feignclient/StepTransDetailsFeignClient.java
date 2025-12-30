package com.aye.vhmwebclient.feignclient;

import com.aye.commonlib.dto.request.StepTransDetailsLinesRequest;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Miraj
 * @date: 29/12/2025
 * @time: 13:14
 * @project: AyeTKVehicleManagement
 */
@FeignClient(name = "StepTransDetailsFeignClient",
        url = "${web.service.url}/api/step-trans-dtl")
public interface StepTransDetailsFeignClient {
    @PostMapping("/create")
    ResponseEntity<ApiRequestResponse> create(
            @RequestParam String userName,
            @RequestBody StepTransDetailsRequest stepTransRequest
    );

    @GetMapping("/findAllByStepTransId")
    ResponseEntity<ApiRequestResponse> findAllByStepTransId(@RequestParam Long stepTransId);

    @GetMapping("/{id}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id);

    //***Line Section*********************
    @PostMapping("/create-dtl")
    ResponseEntity<ApiRequestResponse> saveStDtlLine(@RequestBody StepTransDetailsLinesRequest stepTrnsDtlLnsReq,
                                                     @RequestParam String userName);

    @GetMapping("/findStDtlLineById")
    ResponseEntity<ApiRequestResponse> findStDtlLineById(@RequestParam Long stepTransDtlLnId);

    @GetMapping("/findAllByStTrnDtlId")
    ResponseEntity<ApiRequestResponse> findAllByStTrnDtlId(@RequestParam Long stepTransDtlId);
}

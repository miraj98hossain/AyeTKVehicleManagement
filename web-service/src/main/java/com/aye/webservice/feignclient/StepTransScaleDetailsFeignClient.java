package com.aye.webservice.feignclient;

import com.aye.dtoLib.dto.response.StepTransScaleDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Miraj
 * @date: 06/05/2026
 * @time: 8:08 pm
 */
@FeignClient(name = "StepTransScaleDetailsFeignClient",
        url = "${backend.service.url}/api/step-trans-scale-details")
public interface StepTransScaleDetailsFeignClient {
    @PostMapping("/save")
    ResponseEntity<StepTransScaleDetailsDto> save(@RequestBody StepTransScaleDetailsDto stepTransScaleDetailsDto, @RequestParam String currentUser);

    @GetMapping("/findById")
    ResponseEntity<StepTransScaleDetailsDto> findById(@RequestParam Long stepTransId,
                                                      @RequestParam Long stepTransLinesId,
                                                      @RequestParam Long stepSetupDetailsId);
}

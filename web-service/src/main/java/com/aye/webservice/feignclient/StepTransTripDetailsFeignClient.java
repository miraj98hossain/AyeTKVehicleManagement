package com.aye.webservice.feignclient;

import com.aye.dto.response.StepTransTripDetailsDto;
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
@FeignClient(name = "StepTransTripDetailsFeignClient",
        url = "${backend.service.url}/api/step-trans-trip-details")
public interface StepTransTripDetailsFeignClient {
    @PostMapping("/save")
    ResponseEntity<StepTransTripDetailsDto> save(@RequestBody StepTransTripDetailsDto stepTransTripDetailsDto, @RequestParam String currentUser);

    @GetMapping("/findById")
    ResponseEntity<StepTransTripDetailsDto> findById(@RequestParam Long stepTransId, @RequestParam Long stepTransLinesId);
}

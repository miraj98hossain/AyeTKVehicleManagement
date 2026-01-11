package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Miraj
 * @date: 08/01/2026
 * @time: 12:33
 */

@FeignClient(name = "XxtkgTripDelvDtlVFeignClinet",
        url = "${backend.service.url}/api/trip-dtls")
public interface XxtkgTripDelvDtlVFeignClinet {


    @GetMapping("/filterChallanNumber")
    ResponseEntity<ApiRequestResponse> filterChallanNumber(@RequestParam Long orgId,
                                                           @RequestParam Long invOrgId,
                                                           @RequestParam String searchWords);
}

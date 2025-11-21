package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "StepServiceFeignClient",
        url = "${backend.service.url}/api/mtrns-count")
public interface MTrnsCountFeignController {
    
    @GetMapping("/findAll")
    ResponseEntity<ApiRequestResponse> findAll();

}

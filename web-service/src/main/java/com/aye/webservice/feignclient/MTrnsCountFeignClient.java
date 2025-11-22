package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "MTrnsCountFeignClient",
        url = "${backend.service.url}/api/mtrns-count")
public interface MTrnsCountFeignClient {

    @GetMapping("/findAll")
    ResponseEntity<ApiRequestResponse> findAll();

}

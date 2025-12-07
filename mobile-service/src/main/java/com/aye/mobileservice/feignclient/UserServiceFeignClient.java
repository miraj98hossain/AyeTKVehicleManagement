package com.aye.mobileservice.feignclient;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "UserServiceFeignClient",
        url = "${backend.service.url}/api/user")
public interface UserServiceFeignClient {
    @GetMapping("/findByUserName")
    ResponseEntity<ApiRequestResponse> findByUserName(@RequestParam String username);
}

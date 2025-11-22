package com.aye.webservice.feignclient;


import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "UserMenuFeignClient",
        url = "${backend.service.url}/api/user-access")
public interface UserMenuFeignClient {
    @GetMapping
    ResponseEntity<ApiRequestResponse> getUserAccessByUserName(@RequestParam String username);
}

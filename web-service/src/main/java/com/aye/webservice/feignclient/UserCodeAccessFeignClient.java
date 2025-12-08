package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.request.UserCodeAccessRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "UserCodeAccessFeignClient",
        url = "${backend.service.url}/api/user-code-access")
public interface UserCodeAccessFeignClient {
    @PostMapping("/save")
    ResponseEntity<ApiRequestResponse> save(@RequestBody UserCodeAccessRequest userCodeAccess);

    @GetMapping("/{id}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long userCodeAccessId);

    @GetMapping("/findAllByUser/{id}")
    ResponseEntity<ApiRequestResponse> findAllByUser(@PathVariable("id") Integer userId);
}

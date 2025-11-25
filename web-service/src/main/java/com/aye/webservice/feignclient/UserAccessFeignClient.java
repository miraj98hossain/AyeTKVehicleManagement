package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.request.UserAccessRequest;
import com.aye.commonlib.dto.request.UserAccessTemltDtlRequest;
import com.aye.commonlib.dto.request.UserAccessTempltRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "UserAccessFeignClient",
        url = "${backend.service.url}/api/user-access")
public interface UserAccessFeignClient {

    @GetMapping("/getAllTemplet")
    ResponseEntity<ApiRequestResponse> getAllTemplet();

    @GetMapping("/findByUserId/{userId}")
    ResponseEntity<ApiRequestResponse> findByUserId(@PathVariable("userId") Integer userId);

    @PostMapping("/saveDtlLine")
    ResponseEntity<ApiRequestResponse> saveDtlLine(@RequestBody UserAccessRequest userAccessRequest);

    @PostMapping("/saveUserAccessTemp")
    ResponseEntity<ApiRequestResponse> saveUserAccessTemp(@Valid @RequestBody UserAccessTempltRequest userAccessTempRequest);

    @PostMapping("/saveUserAccessTempDtl")
    ResponseEntity<ApiRequestResponse> saveUserAccessTempDtl(@Valid @RequestBody UserAccessTemltDtlRequest userAccessTemltDtlRequest);

    @GetMapping("/findTempById/{id}")
    ResponseEntity<ApiRequestResponse> findTempById(@PathVariable("id") Integer id);

    @GetMapping("/findTempDtlByDtlId/{id}")
    ResponseEntity<ApiRequestResponse> findTempDtlByDtlId(@PathVariable("id") Integer id);

    @GetMapping("/findByTempHdrId/{id}")
    ResponseEntity<ApiRequestResponse> findByTempHdrId(@PathVariable("id") Integer id);

    @GetMapping("/findUserAccessById/{id}")
    ResponseEntity<ApiRequestResponse> findUserAccessById(@PathVariable("id") Integer id);
}

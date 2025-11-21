package com.aye.webservice.feignclient;


import com.aye.commonlib.dto.request.MUserRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "StepServiceFeignClient", url = "${backend.service.url}/api/user")
public interface UserFeignController {

    @GetMapping("/findAllUser")
    ResponseEntity<ApiRequestResponse> findAllUser();

    @GetMapping("/findByUserName")
    ResponseEntity<ApiRequestResponse> findByUserName(@RequestParam String username);

    @GetMapping("/findUser")
    ResponseEntity<ApiRequestResponse> findUser(@RequestParam("name") String name);

    @GetMapping("/{userId}")
    ResponseEntity<ApiRequestResponse> findAllUser(@PathVariable("userId") Integer userId);

    @GetMapping("/findAllRoles")
    ResponseEntity<ApiRequestResponse> findAllRoles();

    @PostMapping()
    ResponseEntity<ApiRequestResponse> saveUser(@Valid @RequestBody MUserRequest mUserRequest);
}

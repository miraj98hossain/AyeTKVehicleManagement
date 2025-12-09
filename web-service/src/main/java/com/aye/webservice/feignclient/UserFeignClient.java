package com.aye.webservice.feignclient;


import com.aye.commonlib.dto.request.MUserRequest;
import com.aye.commonlib.dto.request.UserSearchRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "UserFeignClient", url = "${backend.service.url}/api/user")
public interface UserFeignClient {

    @GetMapping("/findAllUser")
    ResponseEntity<ApiRequestResponse> findAllUser();

    @GetMapping("/findByUserName")
    ResponseEntity<ApiRequestResponse> findByUserName(@RequestParam String username);

    @GetMapping("/findUser")
    ResponseEntity<ApiRequestResponse> findUser(@RequestParam("name") String name);

    @GetMapping("/findById/{userId}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("userId") Integer userId);

    @GetMapping("/findAllRoles")
    ResponseEntity<ApiRequestResponse> findAllRoles();

    @GetMapping("/findByUserNameLike")
    ResponseEntity<ApiRequestResponse> findByUserNameLike(@RequestParam("username") String username);

    @PostMapping("/saveUser")
    ResponseEntity<ApiRequestResponse> saveUser(@Valid @RequestBody MUserRequest mUserRequest, @RequestParam String username);

    @GetMapping("/filterUsers")
    ResponseEntity<ApiRequestResponse> filterUsers(@RequestBody UserSearchRequest searchRequest);

}

package com.aye.webservice.feignclient;


import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menus")
public interface UserMenuFeignController {
    @GetMapping
    ResponseEntity<ApiRequestResponse> getUserAccessByUserName(@RequestParam String username);
}

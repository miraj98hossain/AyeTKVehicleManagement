package com.aye.backendservice.controller;

import com.aye.backendservice.service.UserMenuBService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menus")
public class UserMenuController {
    @Autowired
    UserMenuBService userMenuBService;

    @GetMapping
    public ResponseEntity<ApiRequestResponse> getUserAccessByUserName(@RequestParam String username,
                                                                      @RequestParam String roleType) {
        return ResponseEntity.ok(userMenuBService.getUserAccessByUserName(username, roleType));
    }
}

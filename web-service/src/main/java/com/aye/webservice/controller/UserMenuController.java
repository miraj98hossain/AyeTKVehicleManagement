package com.aye.webservice.controller;


import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.UserMenuService;
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
    UserMenuService userMenuService;

    @GetMapping
    public ResponseEntity<ApiRequestResponse> getUserAccessByUserName(@RequestParam String username,
                                                                      @RequestParam String roleType) {
        return ResponseEntity.ok(userMenuService.getUserAccessByUserName(username, roleType));
    }
}

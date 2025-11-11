package com.aye.mobileservice.controller;

import com.aye.commonlib.dto.response.UserAccessTemltDtlResponse;
import com.aye.mobileservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<List<UserAccessTemltDtlResponse>> login() {
        return ResponseEntity.ok().body(authService.getMenu(SecurityContextHolder
                .getContext().getAuthentication().getName()));
    }
}

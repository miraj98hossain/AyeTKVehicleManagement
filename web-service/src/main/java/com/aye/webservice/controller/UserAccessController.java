package com.aye.webservice.controller;

import com.aye.commonlib.dto.request.UserAccessRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.UserAccessService;
import com.aye.webservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-access")
public class UserAccessController {
    @Autowired
    UserService userService;
    @Autowired
    private UserAccessService userAccessService;

    @GetMapping("/getAllTemplet")
    ResponseEntity<ApiRequestResponse> getAllTemplet() {
        return ResponseEntity.ok(this.userAccessService.getAllTemplet());
    }

    @GetMapping("/findByUserId/{userId}")
    ResponseEntity<ApiRequestResponse> findByUserId(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(this.userAccessService.findByUserId(userId));
    }

    @PostMapping("/saveDtlLine")
    ResponseEntity<ApiRequestResponse> saveDtlLine(@Valid @RequestBody UserAccessRequest userAccessRequest) {
        return ResponseEntity.ok(this.userAccessService.saveDtlLine(userAccessRequest));
    }

}

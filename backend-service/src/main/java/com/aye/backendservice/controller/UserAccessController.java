package com.aye.backendservice.controller;

import com.aye.backendservice.service.UserAccessBService;
import com.aye.backendservice.service.UserService;
import com.aye.commonlib.dto.request.UserAccessRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
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
    private UserAccessBService userAccessBService;

    @GetMapping("/getAllTemplet")
    ResponseEntity<ApiRequestResponse> getAllTemplet() {
        return ResponseEntity.ok(this.userAccessBService.getAllTemplet());
    }

    @GetMapping("/findByUserId/{userId}")
    ResponseEntity<ApiRequestResponse> findByUserId(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(this.userAccessBService.findByUserId(userId));
    }

    @PostMapping("/saveDtlLine")
    ResponseEntity<ApiRequestResponse> saveDtlLine(@Valid @RequestBody UserAccessRequest userAccessRequest) {
        return ResponseEntity.ok(this.userAccessBService.saveDtlLine(userAccessRequest));
    }

}

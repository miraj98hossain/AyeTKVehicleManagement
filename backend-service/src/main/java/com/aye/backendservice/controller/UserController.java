package com.aye.backendservice.controller;

import com.aye.backendservice.service.UserService;
import com.aye.commonlib.dto.request.MUserRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/findAllUser")
    ResponseEntity<ApiRequestResponse> findAllUser() {
        return ResponseEntity.ok(this.userService.findAllUser());
    }

    @GetMapping("/findByUserName")
    public ResponseEntity<ApiRequestResponse> findByUserName(@RequestParam String username) {
        return ResponseEntity.ok(this.userService.findByUserName(username));
    }

    @GetMapping("/findUser")
    public ResponseEntity<ApiRequestResponse> findUser(@RequestParam("name") String name) {
        return ResponseEntity.ok(this.userService.findByUserName(name));
    }

    @GetMapping("/findById/{userId}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(this.userService.findById(userId));
    }

    @GetMapping("/findAllRoles")
    ResponseEntity<ApiRequestResponse> findAllRoles() {
        return ResponseEntity.ok(this.userService.findAllRoles());
    }

    @PostMapping("/saveUser")
    ResponseEntity<ApiRequestResponse> saveUser(@Valid @RequestBody MUserRequest mUserRequest, @RequestParam String username) {
        return ResponseEntity.ok(this.userService.updateUser(mUserRequest, username));
    }

    @GetMapping("/findByUserNameLike")
    ResponseEntity<ApiRequestResponse> findByUserNameLike(@RequestParam("username") String username) {
        return ResponseEntity.ok(this.userService.findByUserNameLike(username));
    }
}

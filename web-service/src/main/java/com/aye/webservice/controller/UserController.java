package com.aye.webservice.controller;


import com.aye.commonlib.dto.request.MUserRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.UserService;
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

    @GetMapping("/{userId}")
    ResponseEntity<ApiRequestResponse> findAllUser(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(this.userService.findAllUser(userId));
    }

    @GetMapping("/findAllRoles")
    ResponseEntity<ApiRequestResponse> findAllRoles() {
        return ResponseEntity.ok(this.userService.findAllRoles());
    }

    @PostMapping()
    ResponseEntity<ApiRequestResponse> saveUser(@Valid @RequestBody MUserRequest mUserRequest) {
        return ResponseEntity.ok(this.userService.saveUser(mUserRequest));
    }
}

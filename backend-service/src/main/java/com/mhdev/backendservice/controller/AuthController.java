package com.mhdev.backendservice.controller;

import com.mhdev.backendservice.service.UserService;
import com.mhdev.commonlib.dto.request.LoginReqDto;
import com.mhdev.commonlib.dto.request.RegistrationReqDto;
import com.mhdev.commonlib.dto.response.LoginResDto;
import com.mhdev.commonlib.dto.response.RegistrationResDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    @Autowired
     private UserService userService;

    @PostMapping("registration")
    public ResponseEntity<RegistrationResDto> registration(@Valid @RequestBody RegistrationReqDto registrationReqDto) {
        try{
        var registrationResDto=   userService.saveUser(registrationReqDto);
           return new ResponseEntity<>(registrationResDto, HttpStatus.CREATED);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @PostMapping("login")
    public ResponseEntity<LoginResDto> login(@Valid @RequestBody LoginReqDto loginReqDto) {
        try{
            var loginResDto=   userService.login(loginReqDto);
            return new ResponseEntity<>(loginResDto, HttpStatus.OK);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}

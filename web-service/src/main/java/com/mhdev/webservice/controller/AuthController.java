package com.mhdev.webservice.controller;


import com.mhdev.webservice.dto.requestdto.LoginReqDto;
import com.mhdev.webservice.dto.requestdto.RegistrationReqDto;
import com.mhdev.webservice.dto.responsedto.LoginResDto;
import com.mhdev.webservice.dto.responsedto.RegistrationResDto;

import com.mhdev.webservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    @Autowired
     private AuthService authService;

    @PostMapping("registration")
    public ResponseEntity<RegistrationResDto> registration(@Valid @RequestBody RegistrationReqDto registrationReqDto) {
        try{
        var registrationResDto=   authService.saveUser(registrationReqDto);
           return new ResponseEntity<>(registrationResDto, HttpStatus.CREATED);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @PostMapping("login")
    public ResponseEntity<LoginResDto> login(@Valid @RequestBody LoginReqDto loginReqDto) {
        try{
            var loginResDto=   authService.login(loginReqDto);
            return new ResponseEntity<>(loginResDto, HttpStatus.OK);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}

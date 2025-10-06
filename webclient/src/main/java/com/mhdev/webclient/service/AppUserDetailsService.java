package com.mhdev.webclient.service;


import com.mhdev.webclient.dto.requestdto.RegistrationReqDto;
import com.mhdev.webclient.feignclient.WebServiceAuthFeignClient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppUserDetailsService {

    @Autowired
    private WebServiceAuthFeignClient webServiceAuthFeignClient;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public void createUser(RegistrationReqDto registrationReqDto) {
        registrationReqDto.setPassword(passwordEncoder.encode(registrationReqDto.getPassword()));
        var cuser = webServiceAuthFeignClient.registration(registrationReqDto);
        log.info("User created: " + cuser.getFirstName());
        log.info("User created: " + registrationReqDto.getPassword());
    }

}

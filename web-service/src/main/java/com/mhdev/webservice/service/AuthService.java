package com.mhdev.webservice.service;



import com.mhdev.commonlib.dto.request.LoginReqDto;
import com.mhdev.commonlib.dto.request.RegistrationReqDto;
import com.mhdev.commonlib.dto.response.LoginResDto;
import com.mhdev.commonlib.dto.response.RegistrationResDto;
import com.mhdev.webservice.feignclient.BackendAuthServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private BackendAuthServiceFeignClient backendAuthService;

    public RegistrationResDto saveUser(RegistrationReqDto registrationReqDto){
        return backendAuthService.registration(registrationReqDto);
    }

    public LoginResDto login(LoginReqDto loginReqDto) {
        return backendAuthService.login(loginReqDto);
    }
}

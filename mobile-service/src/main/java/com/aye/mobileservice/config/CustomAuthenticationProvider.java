//package com.aye.mobileservice.config;
//
//import com.aye.commonlib.dto.request.LoginRequest;
//import com.aye.mobileservice.feignclient.WebServiceAuthFeignClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//    @Autowired
//    private WebServiceAuthFeignClient webServiceAuthFeignClient;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        LoginRequest loginReqDto = new LoginRequest();
//        loginReqDto.setUserName(username);
//        loginReqDto.setPassword(password);
//        //TODO
/// /        var loginRes = webServiceAuthFeignClient.login(loginReqDto);
/// /        if (!loginRes.getStatus()) {
/// /            throw new BadCredentialsException("Invalid Credentials");
/// /        }
//        return new UsernamePasswordAuthenticationToken(username, password, List.of(new SimpleGrantedAuthority("ROLE_USER")));
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}

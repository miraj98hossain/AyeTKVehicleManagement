//package com.mhdev.webclient.config;
//
//import com.mhdev.webclient.dto.requestdto.LoginReqDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
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
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        LoginReqDto loginReqDto = new LoginReqDto();
//        loginReqDto.setUsername(username);
//        loginReqDto.setPassword(password);
//        var loginRes = webServiceAuthFeignClient.login(loginReqDto);
//        if(!loginRes.getStatus()){
//            throw new BadCredentialsException("Invalid Credentials");
//        }
//        return new UsernamePasswordAuthenticationToken(username, password, List.of(new SimpleGrantedAuthority("ROLE_USER")));
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}

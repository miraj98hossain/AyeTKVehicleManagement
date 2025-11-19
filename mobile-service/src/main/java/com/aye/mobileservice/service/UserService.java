package com.aye.mobileservice.service;

import com.aye.commonlib.dto.response.MUserResponse;
import com.aye.mobileservice.feignclient.UserServiceFeignClient;
import com.aye.mobileservice.utils.ObjectConversionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserServiceFeignClient userServiceFeignClient;
    @Autowired
    ObjectConversionService objectConversionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var response = userServiceFeignClient.findByUserName(username);
        assert response.getBody() != null;
        try {
            MUserResponse user = (MUserResponse) objectConversionService.convertToObject(response.getBody().getApiRequestResponseDetails().get(0));

            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return List.of();
                }

                @Override
                public String getPassword() {
                    return user.getPassword();
                }

                @Override
                public String getUsername() {
                    return user.getUserName();
                }
            };
        } catch (ClassNotFoundException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

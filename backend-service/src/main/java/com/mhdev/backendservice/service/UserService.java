package com.mhdev.backendservice.service;

import com.mhdev.backendservice.dto.requestdto.LoginReqDto;
import com.mhdev.backendservice.dto.requestdto.RegistrationReqDto;
import com.mhdev.backendservice.dto.responsedto.LoginResDto;
import com.mhdev.backendservice.dto.responsedto.RegistrationResDto;
import com.mhdev.backendservice.entity.AppUser;
import com.mhdev.backendservice.mapper.UserMapper;
import com.mhdev.backendservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public RegistrationResDto saveUser(RegistrationReqDto registrationReqDto){
        AppUser appUser = new AppUser();
        appUser = userMapper.toAppUser(registrationReqDto);
        appUser = userRepository.save(appUser);
        return userMapper.toRegistrationResDto(appUser);
    }

    public LoginResDto login(LoginReqDto loginReqDto) {
      var user= userRepository.findByPhone(loginReqDto.getUsername()).orElseThrow(
               ()-> new EntityNotFoundException("User not found with phone: " + loginReqDto.getUsername())
       );

      LoginResDto loginResDto = new LoginResDto(false);

      if(passwordEncoder.matches(loginReqDto.getPassword(), user.getPassword())){
          loginResDto.setStatus(true);
      }
       return loginResDto;
    }
}

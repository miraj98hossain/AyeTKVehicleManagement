package com.mhdev.crud.service;

import com.mhdev.crud.entity.AppUser;
import com.mhdev.crud.repository.AppUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var appUser = appUserRepo.findByPhone(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with phone: " + username)
        );

        return User.builder().username(appUser.getPhone()).password(appUser.getPassword()).roles("USER").build();
    }

    public void createUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var cuser = appUserRepo.save(user);
        log.info("User created: " + cuser.getFirstName());
    }

}

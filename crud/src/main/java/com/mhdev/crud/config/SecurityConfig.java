package com.mhdev.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.formLogin(
                        formLogin ->
                                formLogin.loginPage("/login").permitAll())
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests.requestMatchers("/registration").permitAll()
                                        .anyRequest().authenticated()

                ).build();



    }
}

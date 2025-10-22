package com.mhdev.webservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "BackendAuthServiceFeignClient",url = "${backend.service.url}${backend.service.auth.prefix}")
public interface BackendAuthServiceFeignClient {
    @PostMapping("registration")
    RegistrationResDto registration(@RequestBody RegistrationReqDto registrationReqDto);
    @PostMapping("login")
    LoginResDto login(@RequestBody LoginReqDto loginReqDto);
}

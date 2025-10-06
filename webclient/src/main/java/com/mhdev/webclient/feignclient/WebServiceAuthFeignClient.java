package com.mhdev.webclient.feignclient;
import com.mhdev.webclient.dto.requestdto.LoginReqDto;
import com.mhdev.webclient.dto.requestdto.RegistrationReqDto;
import com.mhdev.webclient.dto.responsedto.LoginResDto;
import com.mhdev.webclient.dto.responsedto.RegistrationResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "WebServiceAuthFeignClient",url = "${web.service.url}${web.service.auth.prefix}")
public interface WebServiceAuthFeignClient {
    @PostMapping("registration")
    RegistrationResDto registration(@RequestBody RegistrationReqDto registrationReqDto);
    @PostMapping("login")
    LoginResDto login(@RequestBody LoginReqDto loginReqDto);
}

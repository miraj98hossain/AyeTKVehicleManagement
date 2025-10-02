package com.mhdev.webservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "BackendServiceClient",url = "https://localhost:8443")
public interface BackendServiceClient {
    @GetMapping("/api/test")
    public String test();
}

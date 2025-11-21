package com.aye.webservice.feignclient;


import com.aye.commonlib.dto.request.InventoryInformationRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "StepServiceFeignClient",
        url = "${backend.service.url}/api/invInfo")

public interface InvInfoFeignController {
    
    @GetMapping("/findOne/{invId}")
    ResponseEntity<ApiRequestResponse> findOne(@PathVariable("invId") Long invId);

    @PostMapping("/invInfoSave")
    ResponseEntity<ApiRequestResponse> save(InventoryInformationRequest invRequest);

    @GetMapping("/findByOu/{orgId}")
    ResponseEntity<ApiRequestResponse> findByOu(@PathVariable("orgId") Long orgId);
}

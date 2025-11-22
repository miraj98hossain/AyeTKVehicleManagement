package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MTrnsCountFeignClient",
        url = "${backend.service.url}/api/muser-data")
public interface MuserDataFeignClient {
    @GetMapping("/findUserItemByOrgId/{orgId}")
    ResponseEntity<ApiRequestResponse> findByOrgId(@PathVariable("orgId") Long orgId);

    @GetMapping("/findAllCustomerByOrg/{orgId}")
    ResponseEntity<ApiRequestResponse> getAllCustomerByOrg(@PathVariable("orgId") Long orgId);
}

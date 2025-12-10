package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MItemCatComVFeignClient",
        url = "${backend.service.url}/api/m-item-cat-comb")
public interface MItemCatComVFeignClient {
    @GetMapping("/getAllItemCatComb")
    ResponseEntity<ApiRequestResponse> getAllItemCatComb();

    @GetMapping("/filterItemCatComb/{orgId}")
    ResponseEntity<ApiRequestResponse> filterItemCatComb(
            @PathVariable("orgId") Long orgId,
            @RequestParam("searchWords") String searchWords);
}
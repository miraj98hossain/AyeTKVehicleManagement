package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 10:21
 */

@FeignClient(name = "OrderedItemVFeignClient",
        url = "${backend.service.url}/api/orderedItemV")
public interface OrderedItemVFeignClient {

    @GetMapping("/filterOrderedItem")
    ResponseEntity<ApiRequestResponse> filterOrderedItem(@RequestParam Long orgId,
                                                         @RequestParam Long invOrgId,
                                                         @RequestParam String searchWords);
}

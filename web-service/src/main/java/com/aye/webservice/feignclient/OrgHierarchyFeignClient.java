package com.aye.webservice.feignclient;


import com.aye.commonlib.dto.request.OrgHierarchyRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "OrgHierarchyFeignClient",
        url = "${backend.service.url}/api/org-hierarchy")
public interface OrgHierarchyFeignClient {


    @GetMapping("/findById")
    ResponseEntity<ApiRequestResponse> findById(Long orgId);

    @GetMapping("/findOrgPrntById")
    ResponseEntity<ApiRequestResponse> findOrgPrntById(Long orgId);

    @PostMapping("/saveOrg")
    ResponseEntity<ApiRequestResponse> saveOrg(OrgHierarchyRequest orgHierarchyResponse);

    @GetMapping("/getAllOrgHierachy")
    ResponseEntity<ApiRequestResponse> getAllOrgHierachy();
}

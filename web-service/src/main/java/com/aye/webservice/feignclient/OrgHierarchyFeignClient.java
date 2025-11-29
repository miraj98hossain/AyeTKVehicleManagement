package com.aye.webservice.feignclient;


import com.aye.commonlib.dto.request.OrgHierarchyRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "OrgHierarchyFeignClient",
        url = "${backend.service.url}/api/org-hierarchy")
public interface OrgHierarchyFeignClient {

    @GetMapping("/findById/{orgId}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("orgId") Long orgId);

    @GetMapping("/findOrgPrntById/{orgId}")
    ResponseEntity<ApiRequestResponse> findOrgPrntById(@PathVariable("orgId") Long orgId);

    @PostMapping("/saveOrg")
    ResponseEntity<ApiRequestResponse> saveOrg(@Valid @RequestBody OrgHierarchyRequest orgHierarchyResponse);

    @GetMapping("/getAllOrgHierachy")
    ResponseEntity<ApiRequestResponse> getAllOrgHierachy();

    @GetMapping("/findByType")
    ResponseEntity<ApiRequestResponse> findByType(@RequestParam String orgType);
}

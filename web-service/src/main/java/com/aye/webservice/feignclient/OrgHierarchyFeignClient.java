package com.aye.webservice.feignclient;


import com.aye.dto.request.OrgHierarchyRequest;
import com.aye.dto.response.ApiRequestResponse;
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

    @PostMapping("/saveOrg/{userName}")
    ResponseEntity<ApiRequestResponse> saveOrg(@RequestBody OrgHierarchyRequest orgHierarchyResponse, @PathVariable String userName);

    @GetMapping("/getAllOrgHierachy")
    ResponseEntity<ApiRequestResponse> getAllOrgHierachy();

    @GetMapping("/findByType")
    ResponseEntity<ApiRequestResponse> findByType(@RequestParam String orgType);

    @GetMapping("/findAllByParentOrgId")
    ResponseEntity<ApiRequestResponse> findAllByParentOrgId(@RequestParam Long parentOrgId);
}

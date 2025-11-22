package com.aye.webservice.controller;


import com.aye.commonlib.dto.request.OrgHierarchyRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.OrgHierarchyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/org-hierarchy")
public class OrgHierarchyController {
    @Autowired
    OrgHierarchyService orgHierarchyService;

    @GetMapping("/findById")
    public ResponseEntity<ApiRequestResponse> findById(Long orgId) {
        return ResponseEntity.ok(orgHierarchyService.findById(orgId));
    }

    @GetMapping("/findOrgPrntById")
    public ResponseEntity<ApiRequestResponse> findOrgPrntById(Long orgId) {
        return ResponseEntity.ok(orgHierarchyService.findOrgPrntById(orgId));
    }

    @PostMapping("/saveOrg")
    ResponseEntity<ApiRequestResponse> saveOrg(OrgHierarchyRequest orgHierarchyResponse) {
        return ResponseEntity.ok(orgHierarchyService.saveOrg(orgHierarchyResponse));
    }
}

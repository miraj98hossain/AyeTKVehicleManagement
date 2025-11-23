package com.aye.backendservice.controller;

import com.aye.backendservice.service.OrgHierarchyBService;
import com.aye.commonlib.dto.request.OrgHierarchyRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
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
    OrgHierarchyBService orgHierarchyService;

    @GetMapping("/findById")
    public ResponseEntity<ApiRequestResponse> findById(Long orgId) {
        return ResponseEntity.ok(orgHierarchyService.findById(orgId));
    }

    @GetMapping("/findOrgPrntById")
    public ResponseEntity<ApiRequestResponse> findOrgPrntById(Long orgId) {
        return ResponseEntity.ok(orgHierarchyService.findOrgPrntById(orgId));
    }

    @PostMapping("/saveOrg")
    public ResponseEntity<ApiRequestResponse> saveOrg(OrgHierarchyRequest orgHierarchyResponse) {
        return ResponseEntity.ok(orgHierarchyService.saveOrg(orgHierarchyResponse));
    }

    @GetMapping("/getAllOrgHierachy")
    public ResponseEntity<ApiRequestResponse> getAllOrgHierachy() {
        return ResponseEntity.ok(orgHierarchyService.getAllOrgHierachy());
    }
}

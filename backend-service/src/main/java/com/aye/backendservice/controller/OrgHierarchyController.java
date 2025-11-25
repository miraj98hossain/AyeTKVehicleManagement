package com.aye.backendservice.controller;

import com.aye.backendservice.service.OrgHierarchyBService;
import com.aye.commonlib.dto.request.OrgHierarchyRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/org-hierarchy")
public class OrgHierarchyController {
    @Autowired
    OrgHierarchyBService orgHierarchyService;

    @GetMapping("/findById/{orgId}")
    public ResponseEntity<ApiRequestResponse> findById(@PathVariable("orgId") Long orgId) {
        return ResponseEntity.ok(orgHierarchyService.findById(orgId));
    }

    @GetMapping("/findOrgPrntById/{orgId}")
    public ResponseEntity<ApiRequestResponse> findOrgPrntById(@PathVariable("orgId") Long orgId) {
        return ResponseEntity.ok(orgHierarchyService.findOrgPrntById(orgId));
    }

    @PostMapping("/saveOrg")
    public ResponseEntity<ApiRequestResponse> saveOrg(@Valid @RequestBody OrgHierarchyRequest orgHierarchyResponse) {
        return ResponseEntity.ok(orgHierarchyService.saveOrg(orgHierarchyResponse));
    }

    @GetMapping("/getAllOrgHierachy")
    public ResponseEntity<ApiRequestResponse> getAllOrgHierachy() {
        return ResponseEntity.ok(orgHierarchyService.getAllOrgHierachy());
    }
}

package com.aye.backendservice.controller;

import com.aye.backendservice.service.OrgHierarchyBService;
import com.aye.dtoLib.dto.request.OrgHierarchyRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
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

    @PostMapping("/saveOrg/{userName}")
    public ResponseEntity<ApiRequestResponse> saveOrg(@RequestBody OrgHierarchyRequest orgHierarchyResponse, @PathVariable String userName) {
        return ResponseEntity.ok(orgHierarchyService.saveOrg(orgHierarchyResponse, userName));
    }

    @GetMapping("/getAllOrgHierachy")
    public ResponseEntity<ApiRequestResponse> getAllOrgHierachy() {
        return ResponseEntity.ok(orgHierarchyService.getAllOrgHierachy());
    }

    @GetMapping("/findByType")
    public ResponseEntity<ApiRequestResponse> findByType(@RequestParam String orgType) {
        return ResponseEntity.ok().body(this.orgHierarchyService.findByType(orgType));
    }

    @GetMapping("/findAllByParentOrgId")
    public ResponseEntity<ApiRequestResponse> findAllByParentOrgId(@RequestParam Long parentOrgId) {
        return ResponseEntity.ok().body(this.orgHierarchyService.findAllByParentOrgId(parentOrgId));
    }
}

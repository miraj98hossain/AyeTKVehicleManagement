package com.aye.webservice.controller;

import com.aye.commonlib.dto.request.*;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.UserAccessService;
import com.aye.webservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-access")
public class UserAccessController {
    @Autowired
    UserService userService;
    @Autowired
    private UserAccessService userAccessService;

    @GetMapping("/getAllTemplet")
    ResponseEntity<ApiRequestResponse> getAllTemplet() {
        return ResponseEntity.ok(this.userAccessService.getAllTemplet());
    }

    @GetMapping("/findByUserId/{userId}")
    ResponseEntity<ApiRequestResponse> findByUserId(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(this.userAccessService.findByUserId(userId));
    }

    @PostMapping("/saveDtlLine")
    ResponseEntity<ApiRequestResponse> saveDtlLine(@Valid @RequestBody UserAccessRequest userAccessRequest) {
        return ResponseEntity.ok(this.userAccessService.saveDtlLine(userAccessRequest));
    }

    @GetMapping("/findByTempHdrId/{id}")
    public ResponseEntity<ApiRequestResponse> findByTempHdrId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userAccessService.findByTempHdrId(id));
    }

    @PostMapping("/saveUserAccessTemp")
    ResponseEntity<ApiRequestResponse> saveUserAccessTemp(
            @Valid @RequestBody UserAccessTempltRequest userAccessTempRequest) {
        return ResponseEntity.ok(this.userAccessService.saveUserAccessTemp(userAccessTempRequest));
    }

    @PostMapping("/saveUserAccessTempDtl")
    ResponseEntity<ApiRequestResponse> saveUserAccessTempDtl(
            @Valid @RequestBody UserAccessTemltDtlRequest userAccessTemltDtlRequest) {
        return ResponseEntity.ok(this.userAccessService.saveUserAccessTempDtl(userAccessTemltDtlRequest));
    }

    @GetMapping("/findUserAccessById/{id}")
    public ResponseEntity<ApiRequestResponse> findUserAccessById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userAccessService.findUserAccessById(id));
    }

    @GetMapping("/findTempById/{id}")
    ResponseEntity<ApiRequestResponse> findTempById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userAccessService.findTempById(id));
    }

    @GetMapping("/findTempDtlByDtlId/{id}")
    ResponseEntity<ApiRequestResponse> findTempDtlByDtlId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userAccessService.findTempDtlByDtlId(id));
    }

    //***********************Inventory Orgs**********************************
    @GetMapping("/findAllUsrAccessOrg")
    public ResponseEntity<ApiRequestResponse> findAllUsrAccessOrg() {
        return ResponseEntity.ok().body(this.userAccessService.findAllUsrAccessOrg());
    }

    @GetMapping("/findByInvOrgs/{id}")
    public ResponseEntity<ApiRequestResponse> findByInvOrgs(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.userAccessService.findByInvOrgs(id));
    }

    @GetMapping("/findUsrAccInvOrgByTempDtlId/{tempDtlId}")
    public ResponseEntity<ApiRequestResponse> findUsrAccInvOrgByTempDtlId(@PathVariable("tempDtlId") Integer tempDtlId) {
        return ResponseEntity.ok().body(this.userAccessService.findUsrAccInvOrgByTempDtlId(tempDtlId));
    }

    @GetMapping("/findUsrAccessInvOrgById/{id}")
    public ResponseEntity<ApiRequestResponse> findUsrAccessInvOrgById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.userAccessService.findUsrAccessInvOrgById(id));
    }

    @PostMapping("/saveUsrAccessInvOrg")
    public ResponseEntity<ApiRequestResponse> saveUsrAccessInvOrg(@Valid
                                                                  @RequestBody UserAccessInvOrgRequest userAccessInvOrgRequest,
                                                                  @RequestParam String username) {
        return ResponseEntity.ok(this.userAccessService.saveUsrAccessInvOrg(userAccessInvOrgRequest, username));
    }

    @DeleteMapping("deleteUsrAccessInvOrg/{id}")
    public ResponseEntity<ApiRequestResponse> deleteUsrAccessInvOrg(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userAccessService.deleteUsrAccessInvOrg(id));
    }

    //****************************************Trans Type*******************************************
    @GetMapping("/findByInvAccess/{userAccessInvOrgId}")
    public ResponseEntity<ApiRequestResponse> findByInvAccess(@PathVariable("userAccessInvOrgId") Long userAccessInvOrgId) {
        return ResponseEntity.ok(this.userAccessService.findByInvAccess(userAccessInvOrgId));
    }

    @GetMapping("/findUserTransactionTypesById/{id}")
    public ResponseEntity<ApiRequestResponse> findUserTransactionTypesById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userAccessService.findUserTransactionTypesById(id));
    }

    @PostMapping("/aveUserTransactionTypes")
    public ResponseEntity<ApiRequestResponse> saveUserTransactionTypes(@Valid @RequestBody UserTransactionTypesRequest ut,
                                                                       @RequestParam String userName) {
        return ResponseEntity.ok(this.userAccessService.saveUserTransactionTypes(ut, userName));
    }

    @DeleteMapping("/deleteUserTransactionTypes/{id}")
    public ResponseEntity<ApiRequestResponse> DeleteUserTransactionTypes(@PathVariable("id") Long utId) {
        return ResponseEntity.ok(this.userAccessService.DeleteUserTransactionTypes(utId));
    }

    @GetMapping("/getOrgHierarchyInvOrgOrderTrnsType/{orgId}/{invOrgId}/{type}")
    public ApiRequestResponse searchOrdTrnsTypesV(@PathVariable("orgId") Long orgId, @PathVariable("invOrgId") Long invOrgId,
                                                  @PathVariable("type") String type) {
        return this.userAccessService.searchOrdTrnsTypesV(orgId, invOrgId, type);
    }

}

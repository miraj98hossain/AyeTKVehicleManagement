package com.aye.backendservice.controller;

import com.aye.backendservice.service.UserAccessBService;
import com.aye.commonlib.dto.request.*;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-access")
public class UserAccessController {
    @Autowired
    private UserAccessBService userAccessBService;

    @GetMapping("/getAllTemplet")
    ResponseEntity<ApiRequestResponse> getAllTemplet() {
        return ResponseEntity.ok(this.userAccessBService.getAllTemplet());
    }

    @GetMapping("/findByUserId/{userId}")
    ResponseEntity<ApiRequestResponse> findByUserId(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(this.userAccessBService.findByUserId(userId));
    }

    @GetMapping("/findUserAccessById/{id}")
    public ResponseEntity<ApiRequestResponse> findUserAccessById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userAccessBService.findUserAccessById(id));
    }

    @PostMapping("/saveDtlLine")
    ResponseEntity<ApiRequestResponse> saveDtlLine(@Valid @RequestBody UserAccessRequest userAccessRequest) {
        return ResponseEntity.ok(this.userAccessBService.saveDtlLine(userAccessRequest));
    }


    @PostMapping("/saveUserAccessTemp")
    public ResponseEntity<ApiRequestResponse> saveUserAccessTemp(@Valid
                                                                 @RequestBody
                                                                 UserAccessTempltRequest userAccessTempRequest) throws Exception {
        return ResponseEntity.ok(this.userAccessBService.saveUserAccessTemp(userAccessTempRequest));
    }

    @PostMapping("/saveUserAccessTempDtl")
    ResponseEntity<ApiRequestResponse> saveUserAccessTempDtl(@Valid
                                                             @RequestBody UserAccessTemltDtlRequest
                                                                     userAccessTemltDtlRequest) throws Exception {
        return ResponseEntity.ok(this.userAccessBService.saveUserAccessTempDtl(userAccessTemltDtlRequest));
    }

    @GetMapping("/findTempById/{id}")
    public ResponseEntity<ApiRequestResponse> findTempById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userAccessBService.findTempById(id));
    }

    @GetMapping("/findTempDtlByDtlId/{id}")
    public ResponseEntity<ApiRequestResponse> findTempDtlByDtlId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userAccessBService.findTempDtlByDtlId(id));
    }

    @GetMapping("/findByTempHdrId/{id}")
    public ResponseEntity<ApiRequestResponse> findByTempHdrId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userAccessBService.findByTempHdrId(id));
    }

    //***********************Inventory Orgs**********************************
    @GetMapping("/findAllUsrAccessOrg")
    public ResponseEntity<ApiRequestResponse> findAllUsrAccessOrg() {
        return ResponseEntity.ok().body(this.userAccessBService.findAllUsrAccessOrg());
    }

    @GetMapping("/findByInvOrgs/{id}")
    public ResponseEntity<ApiRequestResponse> findByInvOrgs(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.userAccessBService.findByInvOrgs(id));
    }

    @GetMapping("/findUsrAccInvOrgByTempDtlId/{tempDtlId}")
    public ResponseEntity<ApiRequestResponse> findUsrAccInvOrgByTempDtlId(@PathVariable("tempDtlId") Integer tempDtlId) {
        return ResponseEntity.ok().body(this.userAccessBService.findUsrAccInvOrgByTempDtlId(tempDtlId));
    }

    @GetMapping("/findUsrAccessInvOrgById/{id}")
    public ResponseEntity<ApiRequestResponse> findUsrAccessInvOrgById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.userAccessBService.findUsrAccessInvOrgById(id));
    }

    @PostMapping("/saveUsrAccessInvOrg")
    public ResponseEntity<ApiRequestResponse> saveUsrAccessInvOrg(@Valid
                                                                  @RequestBody UserAccessInvOrgRequest userAccessInvOrgRequest,
                                                                  @RequestParam String username) {
        return ResponseEntity.ok(this.userAccessBService.saveUsrAccessInvOrg(userAccessInvOrgRequest, username));
    }

    @DeleteMapping("deleteUsrAccessInvOrg/{id}")
    public ResponseEntity<ApiRequestResponse> deleteUsrAccessInvOrg(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userAccessBService.deleteUsrAccessInvOrg(id));
    }

    //****************************************Trans Type*******************************************
    @GetMapping("/findByInvAccess/{userAccessInvOrgId}")
    ResponseEntity<ApiRequestResponse> findByInvAccess(@PathVariable("userAccessInvOrgId") Long userAccessInvOrgId) {
        return ResponseEntity.ok(this.userAccessBService.findByInvAccess(userAccessInvOrgId));
    }

    @GetMapping("/findUserTransactionTypesById/{id}")
    ResponseEntity<ApiRequestResponse> findUserTransactionTypesById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userAccessBService.findUserTransactionTypesById(id));
    }

    @PostMapping("/aveUserTransactionTypes")
    ResponseEntity<ApiRequestResponse> saveUserTransactionTypes(@Valid @RequestBody UserTransactionTypesRequest ut,
                                                                @RequestParam String userName) {
        return ResponseEntity.ok(this.userAccessBService.saveUserTransactionTypes(ut, userName));
    }

    @DeleteMapping("/deleteUserTransactionTypes/{id}")
    ResponseEntity<ApiRequestResponse> DeleteUserTransactionTypes(@PathVariable("id") Long utId) {
        return ResponseEntity.ok(this.userAccessBService.DeleteUserTransactionTypes(utId));
    }

    @GetMapping("/getOrgHierarchyInvOrgOrderTrnsType/{orgId}/{invOrgId}/{type}")
    public ApiRequestResponse searchOrdTrnsTypesV(@PathVariable("orgId") Long orgId, @PathVariable("invOrgId") Long invOrgId,
                                                  @PathVariable("type") String type) {
        return this.userAccessBService.searchOrdTrnsTypesV(orgId, invOrgId, type);
    }
}

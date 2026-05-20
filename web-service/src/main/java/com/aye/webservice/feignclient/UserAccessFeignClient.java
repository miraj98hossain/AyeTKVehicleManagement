package com.aye.webservice.feignclient;

import com.aye.dtoLib.dto.request.*;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.enums.TrnsType;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "UserAccessFeignClient",
        url = "${backend.service.url}/api/user-access")
public interface UserAccessFeignClient {

    @GetMapping("/getAllTemplet")
    ResponseEntity<ApiRequestResponse> getAllTemplet();

    @GetMapping("/findByUserId/{userId}")
    ResponseEntity<ApiRequestResponse> findByUserId(@PathVariable("userId") Integer userId);

    @PostMapping("/saveDtlLine")
    ResponseEntity<ApiRequestResponse> saveDtlLine(@RequestBody UserAccessRequest userAccessRequest);

    @PostMapping("/saveUserAccessTemp")
    ResponseEntity<ApiRequestResponse> saveUserAccessTemp(@Valid @RequestBody UserAccessTempltRequest userAccessTempRequest);

    @PostMapping("/saveUserAccessTempDtl")
    ResponseEntity<ApiRequestResponse> saveUserAccessTempDtl(@Valid @RequestBody UserAccessTemltDtlRequest userAccessTemltDtlRequest);

    @GetMapping("/findTempById/{id}")
    ResponseEntity<ApiRequestResponse> findTempById(@PathVariable("id") Integer id);

    @GetMapping("/findTempDtlByDtlId/{id}")
    ResponseEntity<ApiRequestResponse> findTempDtlByDtlId(@PathVariable("id") Integer id);

    @GetMapping("/findByTempHdrId/{id}")
    ResponseEntity<ApiRequestResponse> findByTempHdrId(@PathVariable("id") Integer id);

    @GetMapping("/findUserAccessById/{id}")
    ResponseEntity<ApiRequestResponse> findUserAccessById(@PathVariable("id") Integer id);

    @GetMapping("/getUserAccessByUserName")
    ResponseEntity<ApiRequestResponse> getUserAccessByUserName(@RequestParam String username,
                                                               @RequestParam String roleType);

    //***********************Inventory Orgs**********************************
    @GetMapping("/findAllUsrAccessOrg")
    ResponseEntity<ApiRequestResponse> findAllUsrAccessOrg();

    @GetMapping("/findByInvOrgs/{id}")
    ResponseEntity<ApiRequestResponse> findByInvOrgs(@PathVariable("id") Long id);

    @GetMapping("/findUsrAccInvOrgByTempDtlId/{tempDtlId}")
    ResponseEntity<ApiRequestResponse> findByUserAccessTemltDtl(@PathVariable("tempDtlId") Integer tempDtlId);

    @GetMapping("/findUsrAccessInvOrgById/{id}")
    ResponseEntity<ApiRequestResponse> findUsrAccessInvOrgById(@PathVariable("id") Long id);

    @PostMapping("/saveUsrAccessInvOrg")
    ResponseEntity<ApiRequestResponse> saveUsrAccessInvOrg(@Valid
                                                           @RequestBody UserAccessInvOrgRequest userAccessInvOrgRequest,
                                                           @RequestParam String username);

    @DeleteMapping("deleteUsrAccessInvOrg/{id}")
    ResponseEntity<ApiRequestResponse> deleteUsrAccessInvOrg(@PathVariable("id") Long id);

    //****************************************Trans Type*******************************************
    @GetMapping("/findByInvAccess/{userAccessInvOrgId}")
    ResponseEntity<ApiRequestResponse> findByInvAccess(@PathVariable("userAccessInvOrgId") Long userAccessInvOrgId);

    @GetMapping("/findUserTransactionTypesById/{id}")
    ResponseEntity<ApiRequestResponse> findUserTransactionTypesById(@PathVariable("id") Long id);

    @PostMapping("/aveUserTransactionTypes")
    ResponseEntity<ApiRequestResponse> saveUserTransactionTypes(@Valid @RequestBody UserTransactionTypesRequest ut,
                                                                @RequestParam String userName);

    @DeleteMapping("/deleteUserTransactionTypes/{id}")
    ResponseEntity<ApiRequestResponse> DeleteUserTransactionTypes(@PathVariable("id") Long utId);

    @GetMapping("/getOrgHierarchyInvOrgOrderTrnsType/{orgId}/{invOrgId}/{type}")
    ResponseEntity<ApiRequestResponse> searchOrdTrnsTypesV(@PathVariable("orgId") Long orgId, @PathVariable("invOrgId") Long invOrgId,
                                                           @PathVariable("type") String type);

    @GetMapping("/searchInvOrgSubInv")
    ResponseEntity<ApiRequestResponse> searchInvOrgSubInv(@RequestParam("orgId") Long orgId, @RequestParam("invOrgId") Long invOrgId, @RequestParam(value = "subInvName", required = false) String subInvName);

    @GetMapping("/findByTransactionTypes")
    ResponseEntity<ApiRequestResponse> findByTransactionTypes(@RequestParam("userTranTypeId") Long userTranTypeId);

    @PostMapping("/saveUserSubInvAccess")
    ResponseEntity<ApiRequestResponse> saveUserSubInvAccess(@Valid @RequestBody UserSubInvAccessRequest us, @RequestParam("userName") String userName);

    @GetMapping("/findUserSubInvAccessById/{id}")
    ResponseEntity<ApiRequestResponse> findUserSubInvAccessById(@PathVariable("id") Long id);

    @GetMapping("/findByUserTransTypeAccessByInvOrgAndTransType/{invOrgId}/{userId}")
    ResponseEntity<ApiRequestResponse> findByUserTransTypeAccessByInvOrgAndTransType(@PathVariable Long invOrgId,
                                                                                     @PathVariable Integer userId,
                                                                                     @RequestParam TrnsType transType);
}

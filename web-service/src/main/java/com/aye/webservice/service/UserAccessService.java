package com.aye.webservice.service;

import com.aye.dtoLib.dto.request.*;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.enums.TrnsType;
import com.aye.webservice.feignclient.UserAccessFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccessService {
    @Autowired
    private UserAccessFeignClient controller;

    public ApiRequestResponse getAllTemplet() {
        return this.controller.getAllTemplet().getBody();
    }

    public ApiRequestResponse findByUserId(Integer userId) {
        return this.controller.findByUserId(userId).getBody();
    }


    public ApiRequestResponse saveDtlLine(UserAccessRequest userAccessRequest) {
        return this.controller.saveDtlLine(userAccessRequest).getBody();
    }


    public ApiRequestResponse saveUserAccessTemp(
            UserAccessTempltRequest userAccessTempRequest) {
        return this.controller.saveUserAccessTemp(userAccessTempRequest).getBody();
    }

    public ApiRequestResponse saveUserAccessTempDtl(
            UserAccessTemltDtlRequest userAccessTemltDtlRequest) {
        return this.controller.saveUserAccessTempDtl(userAccessTemltDtlRequest).getBody();
    }


    public ApiRequestResponse findTempById(Integer id) {
        return this.controller.findTempById(id).getBody();
    }


    public ApiRequestResponse findTempDtlByDtlId(Integer id) {
        return this.controller.findTempDtlByDtlId(id).getBody();
    }

    public ApiRequestResponse findByTempHdrId(Integer id) {
        return this.controller.findByTempHdrId(id).getBody();
    }

    public ApiRequestResponse findUserAccessById(Integer id) {
        return this.controller.findUserAccessById(id).getBody();
    }

    public ApiRequestResponse getUserAccessByUserName(String username,
                                                      String roleType) {
        return this.controller.getUserAccessByUserName(username, roleType).getBody();
    }

    //***********************Inventory Orgs**********************************

    public ApiRequestResponse findAllUsrAccessOrg() {
        return this.controller.findAllUsrAccessOrg().getBody();
    }


    public ApiRequestResponse findByInvOrgs(Long inventoryInformation) {
        return this.controller.findByInvOrgs(inventoryInformation).getBody();
    }


    public ApiRequestResponse findUsrAccInvOrgByTempDtlId(Integer tempDtlId) {
        return this.controller.findByUserAccessTemltDtl(tempDtlId).getBody();
    }


    public ApiRequestResponse findUsrAccessInvOrgById(Long id) {
        return this.controller.findUsrAccessInvOrgById(id).getBody();
    }


    public ApiRequestResponse saveUsrAccessInvOrg(UserAccessInvOrgRequest userAccessInvOrgRequest, String username) {
        return this.controller.saveUsrAccessInvOrg(userAccessInvOrgRequest, username).getBody();
    }


    public ApiRequestResponse deleteUsrAccessInvOrg(Long id) {
        return this.controller.deleteUsrAccessInvOrg(id).getBody();
    }

    //*****************************Trans Type***************************************
    public ApiRequestResponse findByInvAccess(Long userAccessInvOrgId) {
        return this.controller.findByInvAccess(userAccessInvOrgId).getBody();
    }

    public ApiRequestResponse findUserTransactionTypesById(Long id) {
        return this.controller.findUserTransactionTypesById(id).getBody();
    }

    public ApiRequestResponse saveUserTransactionTypes(UserTransactionTypesRequest ut, String userName) {
        return this.controller.saveUserTransactionTypes(ut, userName).getBody();
    }

    public ApiRequestResponse DeleteUserTransactionTypes(Long utId) {
        return this.controller.DeleteUserTransactionTypes(utId).getBody();
    }

    public ApiRequestResponse searchOrdTrnsTypesV(Long orgId, Long invOrgId, String type) {
        return this.controller.searchOrdTrnsTypesV(orgId, invOrgId, type).getBody();
    }


    public ApiRequestResponse searchInvOrgSubInv(Long orgId, Long invOrgId, String subInvName) {
        return this.controller.searchInvOrgSubInv(orgId, invOrgId, subInvName).getBody();
    }


    public ApiRequestResponse findByTransactionTypes(Long userTranTypeId) {
        return this.controller.findByTransactionTypes(userTranTypeId).getBody();
    }

    public ApiRequestResponse saveUserSubInvAccess(UserSubInvAccessRequest us, String userName) {
        return this.controller.saveUserSubInvAccess(us, userName).getBody();
    }

    public ApiRequestResponse findUserSubInvAccessById(Long id) {
        return this.controller.findUserSubInvAccessById(id).getBody();
    }

    public ApiRequestResponse findByUserTransTypeAccessByInvOrgAndTransType(Long invOrgId, Integer userId, TrnsType transType) {
        return this.controller.findByUserTransTypeAccessByInvOrgAndTransType(invOrgId, userId, transType).getBody();
    }
}

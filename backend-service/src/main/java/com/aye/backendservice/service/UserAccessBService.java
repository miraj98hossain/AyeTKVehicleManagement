package com.aye.backendservice.service;


import com.aye.dtoLib.dto.request.*;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.enums.TrnsType;

public interface UserAccessBService {
    ApiRequestResponse getAllTemplet();

    ApiRequestResponse getUserAccessFromCacheByUserName(String username, String roleType);

    ApiRequestResponse findByUserId(Integer userId);

    ApiRequestResponse saveDtlLine(UserAccessRequest userAccessRequest);

    ApiRequestResponse saveUserAccessTemp(UserAccessTempltRequest userAccessTempRequest) throws Exception;

    ApiRequestResponse saveUserAccessTempDtl(UserAccessTemltDtlRequest userAccessTemltDtlRequest) throws Exception;

    ApiRequestResponse findTempById(Integer id);

    ApiRequestResponse findTempDtlByDtlId(Integer id);

    ApiRequestResponse findByTempHdrId(Integer id);

    void generateCache();

    ApiRequestResponse findUserAccessById(Integer id);


    ApiRequestResponse getUserAccessByUserName(String username, String roleType);

    //***********************Inventory Orgs**********************************
    ApiRequestResponse findAllUsrAccessOrg();

    ApiRequestResponse findByInvOrgs(Long inventoryInformation);

    ApiRequestResponse findUsrAccInvOrgByTempDtlId(Integer tempDtlId);

    ApiRequestResponse findUsrAccessInvOrgById(Long id) throws Exception;

    ApiRequestResponse saveUsrAccessInvOrg(UserAccessInvOrgRequest userAccessInvOrgRequest, String userName) throws Exception;

    ApiRequestResponse deleteUsrAccessInvOrg(Long id);

    //*******************Trans Types***********************************************

    ApiRequestResponse findByInvAccess(Long userAccessInvOrgId);

    ApiRequestResponse findUserTransactionTypesById(Long id);

    ApiRequestResponse saveUserTransactionTypes(UserTransactionTypesRequest ut, String userName) throws Exception;

    ApiRequestResponse DeleteUserTransactionTypes(Long utId);

    //===============UserSubInvAccess======


    ApiRequestResponse findUserSubInvAccessById(Long id);

    ApiRequestResponse searchOrdTrnsTypesV(Long orgId, Long invOrgId, String typ);


    ApiRequestResponse searchInvOrgSubInv(Long orgId, Long invOrgId, String subInvName);

    ApiRequestResponse findByTransactionTypes(Long userTranTypeId);

    ApiRequestResponse saveUserSubInvAccess(UserSubInvAccessRequest us, String userName);

    ApiRequestResponse findByUserTransTypeAccessByInvOrgAndTransType(Long invOrgId, Integer userId, TrnsType transType);
}

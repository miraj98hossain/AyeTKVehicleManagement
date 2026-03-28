package com.aye.backendservice.service;


import com.aye.dtoLib.dto.request.*;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.entitylib.entity.UserSubInvAccess;
import com.aye.entitylib.entity.UserTransactionTypes;

public interface UserAccessBService {
    ApiRequestResponse getAllTemplet();

    ApiRequestResponse findByUserId(Integer userId);

    ApiRequestResponse saveDtlLine(UserAccessRequest userAccessRequest);

    ApiRequestResponse saveUserAccessTemp(UserAccessTempltRequest userAccessTempRequest) throws Exception;

    ApiRequestResponse saveUserAccessTempDtl(UserAccessTemltDtlRequest userAccessTemltDtlRequest) throws Exception;

    ApiRequestResponse findTempById(Integer id);

    ApiRequestResponse findTempDtlByDtlId(Integer id);

    ApiRequestResponse findByTempHdrId(Integer id);

    ApiRequestResponse findUserAccessById(Integer id);


    //***********************Inventory Orgs**********************************
    ApiRequestResponse findAllUsrAccessOrg();

    ApiRequestResponse findByInvOrgs(Long inventoryInformation);

    ApiRequestResponse findUsrAccInvOrgByTempDtlId(Integer tempDtlId);

    ApiRequestResponse findUsrAccessInvOrgById(Long id);

    ApiRequestResponse saveUsrAccessInvOrg(UserAccessInvOrgRequest userAccessInvOrgRequest, String userName);

    ApiRequestResponse deleteUsrAccessInvOrg(Long id);

    //*******************Trans Types***********************************************

    ApiRequestResponse findByInvAccess(Long userAccessInvOrgId);

    ApiRequestResponse findUserTransactionTypesById(Long id);

    ApiRequestResponse saveUserTransactionTypes(UserTransactionTypesRequest ut, String userName);

    ApiRequestResponse DeleteUserTransactionTypes(Long utId);

    //===============UserSubInvAccess======

    ApiRequestResponse findByTransactionTypes(UserTransactionTypes userTransactionTypes);

    ApiRequestResponse findUserSubInvAccessById(Long id);

    ApiRequestResponse saveUserSubInvAccess(UserSubInvAccess us, String userName);

    ApiRequestResponse deleteUserSubInvAccess(UserSubInvAccess us);

    ApiRequestResponse searchOrdTrnsTypesV(Long orgId, Long invOrgId, String typ);


    ApiRequestResponse searchInvOrgSubInv(Long orgId, Long invOrgId, String subInvName);

    ApiRequestResponse findByTransactionTypes(Long userTranTypeId);

    ApiRequestResponse saveUserSubInvAccess(UserSubInvAccessRequest us, String userName);
}

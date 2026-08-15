package com.aye.backendservice.service.orgManagement;


import com.aye.entity.InvOrgSubInvV;
import com.aye.entity.UserAccessInvOrg;
import com.aye.entity.UserSubInvAccess;
import com.aye.entity.UserTransactionTypes;
import com.aye.entity.order.OrdTrnsTypesV;
import com.aye.enums.TrnsType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by toufiq on 11/6/2021.
 */
public interface UserTrnsAndSubinvAccessService {

    List<UserTransactionTypes> findByInvAccess(UserAccessInvOrg userAccessInvOrg);

    List<UserTransactionTypes> findByInvAccess(Long userAccessInvOrgId);

    UserTransactionTypes findUserTransactionTypesById(Long id);

    void saveUserTransactionTypes(UserTransactionTypes ut, String userName);

    void DeleteUserTransactionTypes(UserTransactionTypes ut);

    void DeleteUserTransactionTypes(Long utid);

    //===============UserSubInvAccess======

    List<UserSubInvAccess> findByTransactionTypes(UserTransactionTypes userTransactionTypes);

    UserSubInvAccess findUserSubInvAccessById(Long id);

    void saveUserSubInvAccess(UserSubInvAccess us, String userName);

    void deleteUserSubInvAccess(UserSubInvAccess us);


    List<OrdTrnsTypesV> searchOrdTrnsTypesV(OrdTrnsTypesV v);


    List<InvOrgSubInvV> searchInvOrgSubInv(InvOrgSubInvV v);


    @Transactional
    List<UserTransactionTypes> findByUserTransTypeAccessByInvOrgAndTransType(Long invOrgId, Integer userId, TrnsType transType);
}

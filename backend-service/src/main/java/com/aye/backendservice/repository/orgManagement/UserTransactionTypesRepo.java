package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.UserAccessInvOrg;
import com.aye.entity.UserTransactionTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by toufiq on 10/3/2021.
 */
public interface UserTransactionTypesRepo extends JpaRepository<UserTransactionTypes, Long>, JpaSpecificationExecutor<UserTransactionTypes> {

    List<UserTransactionTypes> findByUserAccessInvOrg(UserAccessInvOrg userAccessInvOrg);

    List<UserTransactionTypes> findByUserAccessInvOrg_Id(Long userAccessInvOrgId);
}

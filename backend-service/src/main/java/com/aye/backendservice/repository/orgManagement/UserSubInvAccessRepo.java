package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.UserSubInvAccess;
import com.aye.entity.UserTransactionTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by toufiq on 10/3/2021.
 */
public interface UserSubInvAccessRepo extends JpaRepository<UserSubInvAccess, Long> {
    List<UserSubInvAccess> findByUserTransactionTypes(UserTransactionTypes transactionTypes);

    List<UserSubInvAccess> findByUserTransactionTypes_Id(Long userTransactionTypesId);
}

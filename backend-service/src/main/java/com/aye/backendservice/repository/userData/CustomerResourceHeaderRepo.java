package com.aye.backendservice.repository.userData;


//import com.aye.entity.user.CustomerResourceHeader;

import com.aye.entity.user.CustomerResourceHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by toufiq on 6/27/2021.
 */
public interface CustomerResourceHeaderRepo extends JpaRepository<CustomerResourceHeader, Integer>, JpaSpecificationExecutor<CustomerResourceHeader> {

    CustomerResourceHeader findByOrgIdAndCustAccountId(Long orgId, Integer cstAcctId);

    List<CustomerResourceHeader> findByOrgId(Long orgId);
}

package com.aye.backendservice.repository.user;

import com.aye.entity.user.CustProfile;
import com.aye.entity.user.Muser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Toufiq on 8/19/2019.
 */
public interface CustProfileRepo extends JpaRepository<CustProfile, Integer>, JpaSpecificationExecutor<CustProfile> {
    CustProfile findByCustAcctIdAndOrgId(Integer c, Integer o);

    CustProfile findByCustAcctId(Integer c);

    CustProfile findByUserIdAndOrgId(Muser muser, Integer orgId);

}

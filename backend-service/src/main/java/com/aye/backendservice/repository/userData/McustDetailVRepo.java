package com.aye.backendservice.repository.userData;


import com.aye.entity.user.userData.McustDetailV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by toufiq on 8/23/2021.
 */
public interface McustDetailVRepo extends JpaRepository<McustDetailV, Long>, JpaSpecificationExecutor<McustDetailV> {
    McustDetailV findByOrgIdAndPartyId(Long orgId, Integer partyId);

    McustDetailV findByOrgIdAndCustAcctId(Long orgId, Integer custAcctId);

    List<McustDetailV> findByOrgId(Long orgId);

}

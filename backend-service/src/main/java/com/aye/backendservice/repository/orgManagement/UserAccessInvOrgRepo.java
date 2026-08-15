package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.InventoryInformations;
import com.aye.entity.UserAccessInvOrg;
import com.aye.entity.UserAccessTemltDtl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Created by Toufiq on 3/30/2019.
 */
public interface UserAccessInvOrgRepo extends JpaRepository<UserAccessInvOrg, Long> {
    List<UserAccessInvOrg> findByUserAccessTemltDtl(UserAccessTemltDtl userAccessTemltDtl);

    List<UserAccessInvOrg> findByInvOrgs(InventoryInformations invOrgs);

    List<UserAccessInvOrg> findAllByUserAccessTemltDtl_Id(Integer userAccessTemltDtlId);
}

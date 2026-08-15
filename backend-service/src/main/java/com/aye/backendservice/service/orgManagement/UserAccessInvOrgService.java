package com.aye.backendservice.service.orgManagement;


import com.aye.entity.OrgHierarchy;
import com.aye.entity.UserAccessInvOrg;
import com.aye.entity.UserAccessTemltDtl;

import java.util.List;

/**
 * Created by Toufiq on 3/30/2019.
 */
public interface UserAccessInvOrgService {

    List<UserAccessInvOrg> findAll();

    List<UserAccessInvOrg> findByInvOrgs(Long inventoryInformations);

    List<UserAccessInvOrg> findByUserAccessTemltDtl(UserAccessTemltDtl userAccessTemltDtl);

    List<UserAccessInvOrg> findByUserAccessTemltDtl(Integer tempDtlId);

    UserAccessInvOrg findById(Long id);

    void save(UserAccessInvOrg userAccessInvOrg);

    void delete(UserAccessInvOrg userAccessInvOrg);

    void delete(Long id);

    List<UserAccessInvOrg> findByUserAccessTemltDtlAndOrg(UserAccessTemltDtl uatd, OrgHierarchy oh);
}

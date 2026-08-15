package com.aye.backendservice.service.orgManagement;


import com.aye.entity.OrgHierarchy;
import com.aye.enums.OrgType;

import java.util.List;

/**
 * Created by Toufiq on 2/1/2019.
 */
public interface OrgHierarchyService {

    List<OrgHierarchy> findall();

    OrgHierarchy findById(Long orgId);

    List<OrgHierarchy> findAllBg();

    void saveOrg(OrgHierarchy orgHierarchy);

    List<OrgHierarchy> findByType(OrgType OrgType);

    List<OrgHierarchy> findByHierarchy(OrgHierarchy hierarchy);
//    public List<ItemOrg> findByInvOrgWithItem (OrgHierarchy io,Long itemId);
}

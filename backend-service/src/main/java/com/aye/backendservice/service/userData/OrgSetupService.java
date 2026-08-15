package com.aye.backendservice.service.userData;


import com.aye.dto.response.OrgSetupDto;
import com.aye.entity.OrgSetup;

import java.util.List;

/**
 * Created by Toufiq on 10/13/2019.
 */
public interface OrgSetupService {
    List<OrgSetup> findAll();

    OrgSetup findByOrgId(Long id);

    List<OrgSetupDto> findAllInterOrg(Long orgId);
}

package com.aye.backendservice.service.userData;


import com.aye.backendservice.repository.userData.OrgSetupRepo;
import com.aye.dto.response.OrgSetupDto;
import com.aye.entity.OrgSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Toufiq on 10/13/2019.
 */
@Service
public class OrgSetupServiceImpl implements OrgSetupService {

    @Autowired
    private OrgSetupRepo orgSetupRepo;

    private List<OrgSetupDto> orgSetupDtos;

    @Override
    public List<OrgSetup> findAll() {
        return this.orgSetupRepo.findAll();
    }

    @Override
    public OrgSetup findByOrgId(Long id) {
        return this.orgSetupRepo.findByOrgId(id);
    }

    @Override
    public List<OrgSetupDto> findAllInterOrg(Long orgId) {
        orgSetupDtos = new ArrayList<>();
        OrgSetup setup = this.findByOrgId(orgId);
        OrgSetupDto dto = new OrgSetupDto(setup.getOrgId(), setup.getOrgName(), setup.getOrgCode());
        orgSetupDtos.add(dto);
        orgSetupRepo.findAll().forEach(orgSetup -> {
            if (!Objects.equals(orgSetup.getOrgId(), orgId)) {
                OrgSetupDto oDto = new OrgSetupDto(orgSetup.getOrgId(), orgSetup.getOrgName(), orgSetup.getOrgCode());
                orgSetupDtos.add(oDto);

            }
        });
        return orgSetupDtos;
    }


}

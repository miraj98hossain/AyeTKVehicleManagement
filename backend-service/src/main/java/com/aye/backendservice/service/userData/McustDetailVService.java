package com.aye.backendservice.service.userData;


import com.aye.backendservice.repository.userData.McustDetailVRepo;
import com.aye.entity.user.userData.McustDetailV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toufiq on 8/23/2021.
 */
@Service
public class McustDetailVService {
    @Autowired
    private McustDetailVRepo mcustDetailVRepo;

    public McustDetailV findByOrgIdAndPartyId(Long orgId, Integer partyId) {
        return this.mcustDetailVRepo.findByOrgIdAndPartyId(orgId, partyId);
    }

    public McustDetailV findByOrgIdAndCustAcctId(Long orgId, Integer custAcctId) {
        return this.mcustDetailVRepo.findByOrgIdAndCustAcctId(orgId, custAcctId);
    }

    public List<McustDetailV> findByOrgId(Long orgId) {
        return this.mcustDetailVRepo.findByOrgId(orgId);
    }
}

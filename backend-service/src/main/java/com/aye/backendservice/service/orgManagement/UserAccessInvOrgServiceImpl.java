package com.aye.backendservice.service.orgManagement;

//import com.aye.RestfulServer.model.InventoryInformations;


import com.aye.backendservice.repository.orgManagement.UserAccessInvOrgRepo;
import com.aye.entity.InventoryInformations;
import com.aye.entity.OrgHierarchy;
import com.aye.entity.UserAccessInvOrg;
import com.aye.entity.UserAccessTemltDtl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.aye.RestfulServer.repo.InvInformationsRepo;
//import com.aye.RestfulServer.repo.OrgHierarchyRepo;

/**
 * Created by Toufiq on 3/30/2019.
 */
@Service
public class UserAccessInvOrgServiceImpl implements UserAccessInvOrgService {
    @Autowired
    private UserAccessInvOrgRepo userAccessInvOrgRepo;

    @Autowired
    private InvInformationsService invInformationsService;
    private List<UserAccessInvOrg> userAccessInvOrgs;

    @Override

    public List<UserAccessInvOrg> findAll() {
        return this.userAccessInvOrgRepo.findAll();
    }

    @Override

    public List<UserAccessInvOrg> findByInvOrgs(Long inventoryInformations) {
        InventoryInformations one = this.invInformationsService.findOne(inventoryInformations);
        return this.userAccessInvOrgRepo.findByInvOrgs(one);
    }

    @Override

    public List<UserAccessInvOrg> findByUserAccessTemltDtl(UserAccessTemltDtl userAccessTemltDtl) {


        this.userAccessInvOrgs = this.userAccessInvOrgRepo.findByUserAccessTemltDtl(userAccessTemltDtl);

        return this.userAccessInvOrgs;
    }

    @Override

    public List<UserAccessInvOrg> findByUserAccessTemltDtl(Integer tempDtlId) {
        this.userAccessInvOrgs = this.userAccessInvOrgRepo.findAllByUserAccessTemltDtl_Id(tempDtlId);
        return this.userAccessInvOrgs;
    }

    @Override

    public UserAccessInvOrg findById(Long id) {
        return this.userAccessInvOrgRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Invalid UserAccess InvOrg Id"));
//        return this.userAccessInvOrgRepo.findOne(id);
    }

    @Override

    public void save(UserAccessInvOrg userAccessInvOrg) {
        this.userAccessInvOrgRepo.save(userAccessInvOrg);
    }

    @Override

    public void delete(UserAccessInvOrg userAccessInvOrg) {
        this.userAccessInvOrgRepo.delete(userAccessInvOrg);
    }

    @Override

    public void delete(Long id) {
        this.userAccessInvOrgRepo.deleteById(id);
    }

    @Override

    public List<UserAccessInvOrg> findByUserAccessTemltDtlAndOrg(UserAccessTemltDtl uatd, OrgHierarchy oh) {
        return List.of();
    }
}

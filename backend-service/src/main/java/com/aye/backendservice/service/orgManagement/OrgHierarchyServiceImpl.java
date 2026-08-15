package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.orgManagement.OrgHierarchyRepo;
import com.aye.entity.OrgHierarchy;
import com.aye.enums.OrgType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Toufiq on 2/1/2019.
 */
@Service
public class OrgHierarchyServiceImpl implements OrgHierarchyService {

    @Autowired
    public OrgHierarchyRepo orgHierarchyRepo;

    @Override
    public List<OrgHierarchy> findall() {
        return this.orgHierarchyRepo.findAll();
    }

    @Override
    public OrgHierarchy findById(Long orgId) {

//        Optional<OrgHierarchy> orgOptional = this.orgHierarchyRepo.findById(orgId);
//        if (orgOptional.isPresent()){
//            return orgOptional.get();
//
//        }
//        else{
//         return new OrgHierarchy();
//           // alternative processing....
//        }
////        return this.orgHierarchyRepo.getOne(orgId);
////        return this.orgHierarchyRepo.findById(orgId);
        return this.orgHierarchyRepo.findById(orgId).orElse(null);
    }

    @Override
    public List<OrgHierarchy> findAllBg() {
        return this.orgHierarchyRepo.findByTypeOrderById(OrgType.BG);
    }

    @Override

    public void saveOrg(OrgHierarchy orgHierarchy) {
        this.orgHierarchyRepo.save(orgHierarchy);
    }

    @Override
    public List<OrgHierarchy> findByType(OrgType OrgType) {
        //return this.orgHierarchyRepo.findByType(OrgType);
        return this.orgHierarchyRepo.findByTypeOrderById(OrgType);
    }

    @Override
    public List<OrgHierarchy> findByHierarchy(OrgHierarchy hierarchy) {
        return this.orgHierarchyRepo.findByOrgHierarchy(hierarchy);
    }

//    @Override
//    public List<ItemOrg> findByInvOrgWithItem(OrgHierarchy hierarchy, Long itemId) {
//        return this.orgHierarchyRepo.findOrgwithItem(hierarchy.getOrgHierarchy().getId(),itemId);
//    }
}

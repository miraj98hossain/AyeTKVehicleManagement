package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.orgManagement.InvInformationsRepo;
import com.aye.entity.InventoryInformations;
import com.aye.entity.OrgHierarchy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.aye.web.repo.InvRepo.InvInformationsRepo;

/**
 * Created by Toufiq on 3/14/2019.
 */
@Service
public class InvInformationsServiceImpl implements InvInformationsService {

    @Autowired
    private InvInformationsRepo invInformationsRepo;


    @Override
    public List<InventoryInformations> findAll() {
        return this.invInformationsRepo.findAll();
    }

    @Override
    public InventoryInformations findMasterItemInf(Boolean isItemMaster) {
        return this.invInformationsRepo.findByIsItemMaster(isItemMaster);
    }


    @Override
    public InventoryInformations findMasterItemInfBg(Boolean isItemMaster, OrgHierarchy bg) {
        return this.invInformationsRepo.findByIsItemMasterAndOrgHierarchyOrgHierarchy(isItemMaster, bg);
//        return this.invInformationsRepo.findByIsItemMasterAndOrgHierarchy(isItemMaster,bg);
    }

    @Override
    public InventoryInformations findMasterItemInfBg(Boolean isItemMaster, Long orgHierarchyId) {
        return this.invInformationsRepo.findByIsItemMasterAndOrgHierarchy_Id(isItemMaster, orgHierarchyId);
    }

    @Override
    public InventoryInformations findOne(Long id) {
        InventoryInformations inventoryInformations = this.invInformationsRepo.findById(id).orElse(null);
//        return this.invInformationsRepo.findById(id).orElse(null);
        setInvInformationsData(inventoryInformations);
        return inventoryInformations;
//        return this.invInformationsRepo.getOne(id);
//        return this.invInformationsRepo.findOne(id);
    }

    @Override
    public List<InventoryInformations> findByOu(OrgHierarchy orgHierarchy) {
        return this.invInformationsRepo.findByOrgHierarchy(orgHierarchy);
    }

    @Override
    public List<InventoryInformations> findByOu(Long orgHierarchyId) {
//        return this.invInformationsRepo.findByOrgHierarchy(orgHierarchy);
        return this.invInformationsRepo.findByOrgHierarchy_Id(orgHierarchyId);
    }

    @Override
    public List<InventoryInformations> findByOuNotItemOrg(OrgHierarchy orgHierarchy) {
        return this.invInformationsRepo.findByOrgHierarchyAndIsItemMaster(orgHierarchy, false);
    }

    @Override
    public List<InventoryInformations> findByOuNotItemOrg(Long orgHierarchyId) {
        return this.invInformationsRepo.findByOrgHierarchy_IdAndIsItemMaster(orgHierarchyId, false);
    }

    @Override
    public void Save(InventoryInformations inventoryInformations) {
        this.invInformationsRepo.save(inventoryInformations);
    }

    @Override
    public void delete(InventoryInformations inventoryInformations) {
        this.invInformationsRepo.delete(inventoryInformations);
    }

    @Override
    public void delete(Long invInformationId) {
        this.invInformationsRepo.deleteById(invInformationId);
    }

    private void setInvInformationsData(InventoryInformations inventoryInformations) {
//        inventoryInformations.setCogsAccV(this.accCodeCombinitionsVService.findById(inventoryInformations.getCogsAcc().getId()));
//        inventoryInformations.setInventoryApAccV(this.accCodeCombinitionsVService.findById(inventoryInformations.getInventoryApAcc().getId()));
//        inventoryInformations.setMaterialAccV(this.accCodeCombinitionsVService.findById(inventoryInformations.getMaterialAcc().getId()));
//        inventoryInformations.setSalesAccV(this.accCodeCombinitionsVService.findById(inventoryInformations.getSalesAcc().getId()));
    }


}

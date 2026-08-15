package com.aye.backendservice.service.orgManagement;


import com.aye.entity.InventoryInformations;
import com.aye.entity.OrgHierarchy;

import java.util.List;

/**
 * Created by Toufiq on 3/14/2019.
 */
public interface InvInformationsService {

    List<InventoryInformations> findAll();

    InventoryInformations findMasterItemInf(Boolean isItemMaster);

    InventoryInformations findMasterItemInfBg(Boolean isItemMaster, OrgHierarchy bg);

    InventoryInformations findMasterItemInfBg(Boolean isItemMaster, Long orgHierarchyId);

    InventoryInformations findOne(Long id);

    List<InventoryInformations> findByOu(OrgHierarchy orgHierarchy);

    List<InventoryInformations> findByOu(Long orgHierarchyId);

    List<InventoryInformations> findByOuNotItemOrg(OrgHierarchy orgHierarchy);

    List<InventoryInformations> findByOuNotItemOrg(Long orgHierarchyId);

    void Save(InventoryInformations inventoryInformations);

    void delete(InventoryInformations inventoryInformations);

    void delete(Long invLongId);
}

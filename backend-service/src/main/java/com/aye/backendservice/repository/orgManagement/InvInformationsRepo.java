package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.InventoryInformations;
import com.aye.entity.OrgHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Toufiq on 3/14/2019.
 */
public interface InvInformationsRepo extends JpaRepository<InventoryInformations, Long> {
    List<InventoryInformations> findByOrgHierarchy(OrgHierarchy orgHierarchy);

    List<InventoryInformations> findByOrgHierarchyAndIsItemMaster(OrgHierarchy orgHierarchy, Boolean isBoolean);

    InventoryInformations findByIsItemMaster(Boolean isItemMaster);

    InventoryInformations findByIsItemMasterAndOrgHierarchyOrgHierarchy(Boolean isItemMaster, OrgHierarchy orgHierarchy);

    InventoryInformations findByIsItemMasterAndOrgHierarchy(Boolean isItemMaster, OrgHierarchy orgHierarchy);


    List<InventoryInformations> findByOrgHierarchy_Id(Long orgHierarchyId);

    InventoryInformations findByIsItemMasterAndOrgHierarchy_Id(Boolean isItemMaster, Long orgHierarchyId);

    List<InventoryInformations> findByOrgHierarchy_IdAndIsItemMaster(Long orgHierarchyId, boolean b);

    List<InventoryInformations> findByOrgHierarchy_IdAndCodeLikeIgnoreCase(Long orgHierarchyId, String code);
}

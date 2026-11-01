package com.aye.backendservice.postgresRepository;


import com.aye.backendservice.postgresEntity.MItemCatVNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

/**
 * @author: Miraj
 * @date: 02/05/2026
 * @time: 7:33 pm
 */
public interface MItemCatVRepo extends JpaRepository<MItemCatVNew, Long>, JpaSpecificationExecutor<MItemCatVNew> {
    List<MItemCatVNew> findAllByInventoryItemIdIn(Collection<Long> inventoryItemIds);

    List<MItemCatVNew> findByInventoryItemId(Long inventoryItemId);

    List<MItemCatVNew> findByInventoryItemIdAndOrgIdAndOrganizationId(Long inventoryItemId, Long orgId, Long organizationId);
}

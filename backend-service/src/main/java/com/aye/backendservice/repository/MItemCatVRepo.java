package com.aye.backendservice.repository;

import com.aye.entitylib.entity.order.MItemCatV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

/**
 * @author: Miraj
 * @date: 02/05/2026
 * @time: 7:33 pm
 */
public interface MItemCatVRepo extends JpaRepository<MItemCatV, Long>, JpaSpecificationExecutor<MItemCatV> {
    List<MItemCatV> findAllByInventoryItemIdIn(Collection<Long> inventoryItemIds);
}

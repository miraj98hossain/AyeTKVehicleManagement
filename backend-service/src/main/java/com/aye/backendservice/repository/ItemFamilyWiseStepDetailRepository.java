package com.aye.backendservice.repository;

import com.aye.entity.vehicleproject.ItemFamilyWiseStepDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author: Miraj
 * @date: 01/11/2026
 * @time: 12:59 pm
 */
public interface ItemFamilyWiseStepDetailRepository extends JpaRepository<ItemFamilyWiseStepDetail, Long> {
    Optional<ItemFamilyWiseStepDetail> findByOrgIdAndInvOrgIdAndItemFamily(Long orgId, Long invOrgId, String itemFamily);
}

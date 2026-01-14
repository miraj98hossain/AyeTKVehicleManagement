package com.aye.backendservice.repository;


import com.aye.entitylib.entity.ScaleSetup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 16:04
 */
public interface ScaleSetupRepo extends JpaRepository<ScaleSetup, Long> {

    List<ScaleSetup> findAllByOrgHierarchy_Id(Long orgHierarchyId);
}

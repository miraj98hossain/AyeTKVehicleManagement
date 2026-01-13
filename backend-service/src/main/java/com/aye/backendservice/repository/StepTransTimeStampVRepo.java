package com.aye.backendservice.repository;


import com.aye.entitylib.entity.StepTransTimeStampV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: Miraj
 * @date: 01/01/2026
 * @time: 13:37
 * @project: AyeTKVehicleManagement
 */
public interface StepTransTimeStampVRepo extends JpaRepository<StepTransTimeStampV, Long> {
    List<StepTransTimeStampV> findAllByStepSetupDetailsId(Long stepSetupDetailsId);
}

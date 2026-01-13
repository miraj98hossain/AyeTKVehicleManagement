package com.aye.backendservice.repository;


import com.aye.entitylib.entity.StepWiseTransCountV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface StepWiseTransCountVRepo extends JpaRepository<StepWiseTransCountV, Long> {
    List<StepWiseTransCountV> findAllByStepSetupDetailsIdIn(Collection<Long> stepSetupDetailsIds);
}

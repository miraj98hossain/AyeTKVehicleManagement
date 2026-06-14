package com.aye.backendservice.repository;


import com.aye.entitylib.entity.vehicleproject.StrStepWiseTotalTimeV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: Miraj
 * @date: 10/06/2026
 * @time: 4:15 pm
 */
public interface StrStepWiseTotalTimeVRepository extends JpaRepository<StrStepWiseTotalTimeV, Long> {
    List<StrStepWiseTotalTimeV> findAllByStepTransId(Long stepTransId);
}

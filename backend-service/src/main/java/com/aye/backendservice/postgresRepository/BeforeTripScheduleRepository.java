package com.aye.backendservice.postgresRepository;

import com.aye.backendservice.postgresEntity.BeforeTripSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: Miraj
 * @date: 31/10/2026
 * @time: 4:53 pm
 */
public interface BeforeTripScheduleRepository extends JpaRepository<BeforeTripSchedule, Long>, JpaSpecificationExecutor<BeforeTripSchedule> {
}

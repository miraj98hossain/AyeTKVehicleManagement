package com.aye.backendservice.postgresRepository;

import com.aye.backendservice.postgresEntity.BeforeTripOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: Miraj
 * @date: 31/10/2026
 * @time: 3:32 pm
 */
public interface BeforeTripOrderRepository extends JpaRepository<BeforeTripOrder, Long>, JpaSpecificationExecutor<BeforeTripOrder> {
}

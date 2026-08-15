package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.schedule.XxtkgTripDelvDtlV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author: Miraj
 * @date: 08/01/2026
 * @time: 11:46
 */
public interface XxtkgTripDelvDtlVRepo extends JpaRepository<XxtkgTripDelvDtlV, Long>, JpaSpecificationExecutor<XxtkgTripDelvDtlV> {

    List<XxtkgTripDelvDtlV> findByChallanNumber(String challanNumber);
}

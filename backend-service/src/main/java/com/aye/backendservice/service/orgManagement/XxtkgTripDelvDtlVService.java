package com.aye.backendservice.service.orgManagement;


import com.aye.entity.schedule.XxtkgTripDelvDtlV;

import java.util.List;

/**
 * @author: Miraj
 * @date: 08/01/2026
 * @time: 11:50
 */
public interface XxtkgTripDelvDtlVService {

    List<XxtkgTripDelvDtlV> filterChallanNumber(Long orgId, Long invOrgId, String searchWords);

    List<XxtkgTripDelvDtlV> findByChallanNumber(String challanNumber);
}

package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.orgManagement.BeforeTripWDsVRepo;
import com.aye.entity.projection.BeforeTripWDsVProjection;
import com.aye.entity.schedule.BeforeTripWDsV;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 10:43
 * @project: AyeTKVehicleManagement
 */
@Service
public class BeforeTripWDsVServiceImpl implements BeforeTripWDsVService {
    @Autowired
    private BeforeTripWDsVRepo beforeTripWDsVRepo;

    @Override
    @Transactional(readOnly = true)
    public List<BeforeTripWDsVProjection> findScheduleId(Long orgId, Long invOrgId, String searchWords) {
        var list = beforeTripWDsVRepo.findScheduleId(orgId, invOrgId, searchWords);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BeforeTripWDsV> findAllByScheduleNumber(String scheduleNo) {
//        var list = beforeTripWDsVRepo.findAllByScheduleNumber(scheduleNo);
//        return list;
        List<BeforeTripWDsV> list = this.beforeTripWDsVRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("scheduleNumber"), scheduleNo));
            predicates.add(cb.notEqual(root.get("flowStatusCode"), "CLOSED"));
            return cb.and(predicates.toArray(new Predicate[0]));
        });
        return list;
    }
}

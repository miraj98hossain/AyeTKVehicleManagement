package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.orgManagement.BeforeTripVRepo;
import com.aye.entity.projection.BeforeTripVProjection;
import com.aye.entity.schedule.BeforeTripV;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 11:23
 * @project: AyeTKVehicleManagement
 */
@Service
public class BeforeTripVServiceImpl implements BeforeTripVService {
    @Autowired
    private BeforeTripVRepo beforeTripVRepo;

    @Override
    @Transactional(readOnly = true)
    public List<BeforeTripVProjection> getDeliveryNumbers(Long orgId, Long invOrgId, String mode, Long searchWords) {
        return beforeTripVRepo.getDeliveryNumbers(orgId, invOrgId, mode, searchWords);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BeforeTripV> findAllByOrderNumber(Long deliveryNo) {
        var list = this.beforeTripVRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("orderNumber"), deliveryNo));
            predicates.add(cb.notEqual(root.get("flowStatusCode"), "CLOSED"));
            return cb.and(predicates.toArray(new Predicate[0]));
        });
        return list;
    }
}

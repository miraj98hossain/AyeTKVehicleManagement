package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.orgManagement.XxtkgTripDelvDtlVRepo;
import com.aye.entity.schedule.XxtkgTripDelvDtlV;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Miraj
 * @date: 08/01/2026
 * @time: 11:52
 */
@Service
public class XxtkgTripDelvDtlVServiceImpl implements XxtkgTripDelvDtlVService {

    @Autowired
    private XxtkgTripDelvDtlVRepo xxtkgTripDelvDtlVRepo;


    @Override
    public List<XxtkgTripDelvDtlV> filterChallanNumber(Long orgId, Long invOrgId, String searchWords) {
        var list = xxtkgTripDelvDtlVRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("orgId"), orgId));
            predicates.add(cb.equal(root.get("shipFromOrgId"), invOrgId));
            predicates.add(cb.isNotNull(root.get("actualShipmentDate")));
            predicates.add(cb.like(root.get("challanNumber"), "%" + searchWords + "%"));
            return cb.and(predicates.toArray(new Predicate[0]));
        });
        return list;
    }

    @Override
    public List<XxtkgTripDelvDtlV> findByChallanNumber(String challanNumber) {
        var list = this.xxtkgTripDelvDtlVRepo.findByChallanNumber(challanNumber);
        return list;
    }
}

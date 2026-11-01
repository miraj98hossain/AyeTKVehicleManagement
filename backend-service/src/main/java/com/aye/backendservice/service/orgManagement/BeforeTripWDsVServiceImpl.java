package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.postgresEntity.BeforeTripSchedule;
import com.aye.backendservice.postgresRepository.BeforeTripScheduleRepository;
import com.aye.entity.projection.BeforeTripWDsVProjection;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
//    @Autowired
//    private BeforeTripWDsVRepo beforeTripWDsVRepo;

    @Autowired
    private BeforeTripScheduleRepository beforeTripScheduleRepo;

    @Override
    @Transactional(readOnly = true)
    public List<BeforeTripWDsVProjection> findScheduleId(Long orgId, Long invOrgId, String searchWords) {
//        var list = beforeTripWDsVRepo.findScheduleId(orgId, invOrgId, searchWords);
//        return list;
        Specification<BeforeTripSchedule> specification = (root, query, cb) -> {
            if (query != null) {
                query.distinct(true);
            }
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("orgId"), orgId));
            predicates.add(cb.equal(root.get("invOrgId"), invOrgId));
            if (searchWords != null) {
                Expression<String> scheduleNumber = root.get("scheduleNumber");
                predicates.add(cb.like(scheduleNumber, "%" + searchWords + "%"));
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
        return this.beforeTripScheduleRepo.findBy(specification,
                q -> q.as(BeforeTripWDsVProjection.class).all()
        );
    }


    @Transactional(readOnly = true)
    @Override
    public List<BeforeTripSchedule> findAllByScheduleNumber(String scheduleNo) {
//        var list = beforeTripWDsVRepo.findAllByScheduleNumber(scheduleNo);
//        return list;
        List<BeforeTripSchedule> list = this.beforeTripScheduleRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("scheduleNumber"), scheduleNo));
            predicates.add(cb.notEqual(root.get("flowStatusCode"), "CLOSED"));
            return cb.and(predicates.toArray(new Predicate[0]));
        });
        return list;
    }
}

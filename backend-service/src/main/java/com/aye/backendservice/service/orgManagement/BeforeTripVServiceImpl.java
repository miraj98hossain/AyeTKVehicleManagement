package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.postgresEntity.BeforeTripOrder;
import com.aye.backendservice.postgresRepository.BeforeTripOrderRepository;
import com.aye.entity.projection.BeforeTripVProjection;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.query.criteria.JpaExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    //    @Autowired
//    private BeforeTripVRepo beforeTripVRepo;
    @Autowired
    private BeforeTripOrderRepository beforeTripOrderRepo;


    @Override
    public List<BeforeTripVProjection> getDeliveryNumbers(Long orgId, Long invOrgId, String mode, Long searchWords) {

        Specification<BeforeTripOrder> specification = (root, query, cb) -> {
            if (query != null) {
                query.distinct(true);
            }
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("orgId"), orgId));
            predicates.add(cb.equal(root.get("invOrgId"), invOrgId));
            if (searchWords != null) {
                Expression<String> orderNumberAsString =
                        ((JpaExpression<?>) root.get("orderNumber"))
                                .cast(String.class);

                predicates.add(
                        cb.like(orderNumberAsString, "%" + searchWords + "%")
                );
            }

            return cb.and(predicates.toArray(Predicate[]::new));
        };
        return this.beforeTripOrderRepo.findBy(
                specification,
                q -> q.as(BeforeTripVProjection.class).all()
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<BeforeTripOrder> findAllByOrderNumber(Long deliveryNo) {
        var list = this.beforeTripOrderRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("orderNumber"), deliveryNo));
            predicates.add(cb.notEqual(root.get("flowStatusCode"), "CLOSED"));
            return cb.and(predicates.toArray(new Predicate[0]));
        });
        return list;
    }
}

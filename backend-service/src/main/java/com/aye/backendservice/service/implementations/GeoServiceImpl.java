package com.aye.backendservice.service.implementations;


import com.aye.RestfulServer.model.geo.Geo;
import com.aye.RestfulServer.repo.GeoRepo;
import com.aye.backendservice.entity.GeoSerach;
import com.aye.backendservice.service.GeoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Toufiq on 9/24/2019.
 */

@Service
public class GeoServiceImpl implements GeoService {
    @Autowired
    private GeoRepo geoRepo;

    @Override
    public List<Geo> findAll() {
        return this.geoRepo.findAll();
    }

    @Override
    public List<Geo> search(GeoSerach s) {
        List<Geo> geos = this.geoRepo.findAll(new Specification<Geo>() {
            @Override
            public Predicate toPredicate(Root<Geo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (s.getCreatedBy().getId() != null) {
                    predicates.add(cb.equal(root.get("createdBy"), s.getCreatedBy().getId()));
                }

                if (s.getFromDate() != null && s.getToDate() != null) {
//                    predicates.add(cb.between(root.<Date>get("creationDate"), s.getFromDate(), s.getToDate()));
                    predicates.add(cb.between(cb.function("TRUNC", Date.class, root.<Date>get("creationDate")), s.getFromDate(), s.getToDate()));
                }


                if ((s.getTrnsSource() != null)) {
                    predicates.add(cb.equal(root.get("trnsSource"), s.getTrnsSource()));
                }

                if ((s.getTrnsId() != null)) {
                    predicates.add(cb.equal(root.get("trnsId"), s.getTrnsId()));
                }

                query.orderBy(cb.desc(root.get("id")));
//                criteriaQuery.orderBy(cb.asc(root.get("poNumber")));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        });
        return geos;
    }

    @Override
    public void save(Geo geo, String mode) {
        // TODO Auto-generated method stub

        this.geoRepo.save(geo);
        this.geoRepo.flush();

    }


}

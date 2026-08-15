package com.aye.backendservice.service.userData;


import com.aye.backendservice.repository.userData.MItemCatComVRepo;
import com.aye.entity.MItemCatComV;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MItemCatComVServiceImpl implements MItemCatComVService {
    @Autowired
    private MItemCatComVRepo mItemCatComVRepo;


    @Override
    public List<MItemCatComV> getAllItemCatComb() {
        return mItemCatComVRepo.findAll();
    }

    @Override
    public List<MItemCatComV> filterItemCatComb(Long orgId, String searchWords) {
        return mItemCatComVRepo.findAll((root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("orgId"), orgId));
            String search = "%" + searchWords.trim().toLowerCase() + "%";
            Predicate orPredicate = cb.or(
                    cb.like(cb.lower(root.get("category")), search),
                    cb.like(cb.lower(root.get("family")), search),
                    cb.like(cb.lower(root.get("itemClass")), search)
            );
            predicates.add(orPredicate);
            return cb.and(predicates.toArray(new Predicate[0]));
        });

    }
}

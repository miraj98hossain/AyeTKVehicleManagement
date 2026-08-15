package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.orgManagement.UserCodeAccessRepo;
import com.aye.entity.UserCodeAccess;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserCodeAccessServiceImpl implements UserCodeAccessService {
    @Autowired
    UserCodeAccessRepo userCodeAccessRepo;

    @Override
            ()
    public UserCodeAccess save(UserCodeAccess userCodeAccess) {
        return userCodeAccessRepo.save(userCodeAccess);
    }

    @Override
            ()
    public UserCodeAccess findById(Long userCodeAccessId) {
        return userCodeAccessRepo.findById(userCodeAccessId).orElseThrow(
                () -> new EntityNotFoundException("UserCodeAccess with id: " + userCodeAccessId + " not found")
        );
    }

    @Override

    public List<UserCodeAccess> findAllByUser(Integer userId) {
        return userCodeAccessRepo.findAllByUser_Id(userId);
    }

    @Override

    public List<UserCodeAccess> findAllUserItemAccess(String userName) {
        Date today = java.sql.Date.valueOf(LocalDate.now());
        return userCodeAccessRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("user").get("userName"), userName));
            predicates.add(cb.isNotNull(root.get("itemCatComb")));
            predicates.add(cb.lessThanOrEqualTo(root.get("startDate").as(Date.class), today));
            predicates.add(
                    cb.or(
                            cb.greaterThanOrEqualTo(root.get("endDate").as(Date.class), today),
                            cb.isNull(root.get("endDate"))
                    )
            );
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }


    @Override
            ()
    public List<UserCodeAccess> findAllByUser(String userName) {
        Date today = java.sql.Date.valueOf(LocalDate.now());
        return userCodeAccessRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("user").get("userName"), userName));
            predicates.add(cb.lessThanOrEqualTo(root.get("startDate").as(Date.class), today));
            predicates.add(
                    cb.or(
                            cb.greaterThanOrEqualTo(root.get("endDate").as(Date.class), today),
                            cb.isNull(root.get("endDate"))
                    )
            );
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Override

    public void deleteById(Long userCodeAccessId) {
        this.userCodeAccessRepo.deleteById(userCodeAccessId);
    }

}
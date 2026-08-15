package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.orgManagement.InvOrgSubInvVRepo;
import com.aye.backendservice.repository.orgManagement.OrdTrnsTypesVRepo;
import com.aye.backendservice.repository.orgManagement.UserSubInvAccessRepo;
import com.aye.backendservice.repository.orgManagement.UserTransactionTypesRepo;
import com.aye.entity.*;
import com.aye.entity.order.OrdTrnsTypesV;
import com.aye.enums.TrnsType;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by toufiq on 11/6/2021.
 */
@Service
public class UserTrnsAndSubinvAccessServiceImpl implements UserTrnsAndSubinvAccessService {

    @Autowired
    private UserTransactionTypesRepo userTransactionTypesRepo;

    @Autowired
    private UserSubInvAccessRepo userSubInvAccessRepo;


    @Autowired
    private OrdTrnsTypesVRepo ordTrnsTypesVRepo;

    @Autowired
    private InvOrgSubInvVRepo invOrgSubInvVRepo;
    @Autowired
    private CommonColumnServiceImpl commonColumnService;


    @Override
    public List<UserTransactionTypes> findByInvAccess(UserAccessInvOrg userAccessInvOrg) {
        return this.userTransactionTypesRepo.findByUserAccessInvOrg(userAccessInvOrg);
    }

    @Override
    public List<UserTransactionTypes> findByInvAccess(Long userAccessInvOrgId) {
        return this.userTransactionTypesRepo.findByUserAccessInvOrg_Id(userAccessInvOrgId);
    }

    @Override
    public UserTransactionTypes findUserTransactionTypesById(Long id) {
        return this.userTransactionTypesRepo.findById(id).orElse(null);
    }

    @Override
    public void saveUserTransactionTypes(UserTransactionTypes ut, String userName) {
        CommonColumn cc;
        if (ut.getId() == null) {
            cc = new CommonColumn();
            setCommonColumn(cc, "E", userName);
        } else {
            cc = ut.getColumn();
            setCommonColumn(cc, "U", userName);
        }
        ut.setColumn(cc);

        this.userTransactionTypesRepo.save(ut);

    }

    @Override
    public void DeleteUserTransactionTypes(UserTransactionTypes ut) {
        this.userTransactionTypesRepo.delete(ut);
    }

    @Override
    public void DeleteUserTransactionTypes(Long utId) {
        this.userTransactionTypesRepo.deleteById(utId);
    }

    @Override
    public List<UserSubInvAccess> findByTransactionTypes(UserTransactionTypes userTransactionTypes) {
        return this.userSubInvAccessRepo.findByUserTransactionTypes(userTransactionTypes);
    }

    @Override
    public UserSubInvAccess findUserSubInvAccessById(Long id) {
        return this.userSubInvAccessRepo.findById(id).orElse(null);
    }

    @Override
    public void saveUserSubInvAccess(UserSubInvAccess us, String userName) {
        CommonColumn cc;
        if (us.getId() == null) {
            cc = new CommonColumn();
            setCommonColumn(cc, "E", userName);
        } else {
            cc = us.getColumn();
            setCommonColumn(cc, "U", userName);
        }
        us.setColumn(cc);

        this.userSubInvAccessRepo.save(us);

    }

    @Override
    public void deleteUserSubInvAccess(UserSubInvAccess us) {
        this.userSubInvAccessRepo.delete(us);
    }

    @Override
    public List<OrdTrnsTypesV> searchOrdTrnsTypesV(OrdTrnsTypesV v) {
        List<OrdTrnsTypesV> ordTrnsTypesVs = this.ordTrnsTypesVRepo.findAll(new Specification<OrdTrnsTypesV>() {
            @Override
            public Predicate toPredicate(Root<OrdTrnsTypesV> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(cb.equal(root.get("orgHierarchy"), v.getOrgHierarchy()));
                predicates.add(cb.equal(root.get("invOrgs"), v.getInvOrgs()));
                predicates.add(cb.equal(root.get("invOrgs"), v.getInvOrgs()));
                if ((v.getTypeName() != null) && (v.getTypeName() != "")) {
//                    predicates.add(cb.like(cb.upper(root.get("typeName"),"%"+v.getTypeName().toUpperCase()+"%")));
                    predicates.add(cb.like(cb.upper(root.get("typeName")), "%" + v.getTypeName().toUpperCase() + "%"));

                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
        return ordTrnsTypesVs;
    }

    @Override
    public List<InvOrgSubInvV> searchInvOrgSubInv(InvOrgSubInvV v) {
        List<InvOrgSubInvV> invOrgSubInvVs = this.invOrgSubInvVRepo.findAll(new Specification<InvOrgSubInvV>() {
            @Override
            public Predicate toPredicate(Root<InvOrgSubInvV> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(cb.equal(root.get("orgHierarchy"), v.getOrgHierarchy()));
                predicates.add(cb.equal(root.get("invOrgs"), v.getInvOrgs()));
                if ((v.getSubInvName() != null) && (v.getSubInvName() != "")) {
                    predicates.add(cb.like(cb.upper(root.get("subInvName")), "%" + v.getSubInvName().toUpperCase() + "%"));

                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        });
        return invOrgSubInvVs;
    }

    @Override
    public List<UserTransactionTypes> findByUserTransTypeAccessByInvOrgAndTransType(Long invOrgId, Integer userId, TrnsType transType) {
        var list = this.userTransactionTypesRepo.findAll((root, query, cb) -> {
            Date today = java.sql.Date.valueOf(LocalDate.now());
            List<Predicate> predicates = new ArrayList<>();


            Join<UserTransactionTypes, UserAccessInvOrg> invJoin = root.join("userAccessInvOrg");
            Join<UserAccessInvOrg, UserAccessTemltDtl> tempDtlJoin = invJoin.join("userAccessTemltDtl");
            Join<UserAccessTemltDtl, UserAccessTemplt> tempJoin = tempDtlJoin.join("userAccessTemplt");
            Join<UserAccessTemplt, UserAccess> userAccessJoin = tempJoin.join("userAccess");
            predicates.add(cb.equal(userAccessJoin.get("user").get("id"), userId));
            predicates.add(cb.equal(invJoin.get("invOrgs").get("id"), invOrgId));
            predicates.add(cb.equal(root.get("trnsType"), transType.name()));
            predicates.add(cb.lessThanOrEqualTo(root.get("startDate").as(Date.class), today));
            predicates.add(
                    cb.or(
                            cb.greaterThanOrEqualTo(root.get("endDate").as(Date.class), today),
                            cb.isNull(root.get("endDate"))
                    )
            );
            return cb.and(predicates.toArray(new Predicate[0]));
        });
        return list;

    }

    public CommonColumn setCommonColumn(CommonColumn commonColumn, String mode, String userName) {
        return this.commonColumnService.setCommonColumn(commonColumn, mode, userName);
    }
}

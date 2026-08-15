package com.aye.backendservice.service.userData;

import com.aye.backendservice.repository.userData.CustomerResourceHeaderRepo;
import com.aye.backendservice.repository.userData.CustomerResourceLineRepo;
import com.aye.backendservice.repository.userData.EmpResourceRepo;
import com.aye.entity.user.CustomerResourceHeader;
import com.aye.entity.user.CustomerResourceLine;
import com.aye.entity.user.EmpResource;
import com.aye.entity.user.userData.McustDetailV;
import com.aye.enums.RegularData;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by toufiq on 6/27/2021.
 */
@Service
public class CustomerResourceServiceImpl implements CustomerResourceService {


    @Autowired
    private CustomerResourceHeaderRepo customerResourceHeaderRepo;

    @Autowired
    private CustomerResourceLineRepo customerResourceLineRepo;

    @Autowired
    private EmpResourceRepo empResourceRepo;

    @Autowired
    private McustDetailVService mcustDetailVService;

    @Override
    public List<CustomerResourceHeader> findResourceHeaderByOrgId(Long orgId) {
        return this.customerResourceHeaderRepo.findByOrgId(orgId);
    }

    @Override
    public CustomerResourceHeader findResourceHeaderById(Integer id) {
        CustomerResourceHeader cr = this.customerResourceHeaderRepo.findById(id).orElse(null);
        setCutName(cr);
        return cr;
    }

    @Override
    public CustomerResourceHeader findByOrgIdAndCustAccountId(Long orgId, Integer cstAcctId) throws Exception {
        CustomerResourceHeader cr = this.customerResourceHeaderRepo.findByOrgIdAndCustAccountId(orgId, cstAcctId);
        setCutName(cr);
        return cr;
    }

    @Override
    public void saveResourceHeader(CustomerResourceHeader resourceHeader) {
        this.customerResourceHeaderRepo.save(resourceHeader);
    }

    @Override
    public List<CustomerResourceLine> findByCustomerResourceHeader(CustomerResourceHeader customerResourceHeader) {
        return this.customerResourceLineRepo.findByCustomerResourceHeader(customerResourceHeader);
    }

    @Override
    public List<CustomerResourceLine> findActiveByCustomerResourceHeader(CustomerResourceHeader customerResourceHeader) {
        return this.customerResourceLineRepo.findByCustomerResourceHeaderAndResourceStatus(customerResourceHeader, RegularData.ResourceStatus.A);
    }

    @Override
    public CustomerResourceLine findResourceLineById(Integer lineId) {
        return this.customerResourceLineRepo.findById(lineId).orElse(null);
    }

//    @Override
//    public CustomerResourceLine findResourceLineByHeaderAndLineId(CustomerResourceHeader header, Integer lineId) {
//        return this.customerResourceLineRepo.findByCustomerResourceHeaderAndId(header,lineId);
//    }

    @Override
    public void saveResourceLine(CustomerResourceLine resourceLine) {
        this.customerResourceLineRepo.save(resourceLine);
    }

    @Override
    public List<EmpResource> findEmpResourceByOrgId(Long orgId) {
        return this.empResourceRepo.findByOrgIdOrderByIdDesc(orgId);
    }

    @Override
    public EmpResource findEmpResourceById(Integer id) {
        return this.empResourceRepo.findById(id).orElse(null);
    }

    @Override
    public void saveEmpResource(EmpResource empResource) {

        this.empResourceRepo.save(empResource);
    }

    @Override
    public EmpResource findLastRecordByOrg(Long orgId) {
        return this.empResourceRepo.findDistinctEmpResourceRepoByOrgIdOrderByIdDesc(orgId).get(0);
    }

    private void setCutName(CustomerResourceHeader h) throws NullPointerException {
        McustDetailV mc = this.mcustDetailVService.findByOrgIdAndCustAcctId(h.getOrgId(), h.getCustAccountId());
        h.setCustomerName(mc.getPartyName());
    }

    @Override
    public List<CustomerResourceHeader> searchCustomerResourceHeader(CustomerResourceHeader s) {
        List<CustomerResourceHeader> customerResourceHeaders = this.customerResourceHeaderRepo.findAll(new Specification<CustomerResourceHeader>() {
            @Override
            public Predicate toPredicate(Root<CustomerResourceHeader> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.get("orgId"), s.getOrgId()));
                if (s.getCustAccountId() != null) {
                    predicates.add(cb.equal(root.get("custAccountId"), s.getCustAccountId()));
                }

                if (s.getAccountNumber() != null) {
                    predicates.add(cb.equal(root.get("accountNumber"), s.getAccountNumber()));
                }

                if (s.getResourceStatus() != null) {
                    predicates.add(cb.equal(root.get("resourceStatus"), s.getResourceStatus()));
                }

                if (s.getStart_date() != null) {
                    predicates.add(cb.lessThanOrEqualTo(cb.coalesce(root.get("start_date"), new Date()), s.getStart_date()));
                }

                if (s.getEnd_date() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(cb.coalesce(root.get("end_date"), new Date()), s.getEnd_date()));
                }


                query.orderBy(cb.desc(root.get("id")));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });

        customerResourceHeaders.forEach(c -> setCutName(c));
        return customerResourceHeaders;
    }


    @Override
    public List<CustomerResourceLine> searchCustomerResourceLine(CustomerResourceLine s) {
        List<CustomerResourceLine> customerResourceLines = this.customerResourceLineRepo.findAll(new Specification<CustomerResourceLine>() {
            @Override
            public Predicate toPredicate(Root<CustomerResourceLine> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.get("orgId"), s.getOrgId()));

                if (s.getStart_date() != null) {
                    predicates.add(cb.lessThanOrEqualTo(cb.coalesce(root.get("start_date"), new Date()), s.getStart_date()));
                }

                if (s.getEnd_date() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(cb.coalesce(root.get("end_date"), new Date()), s.getEnd_date()));
                }

                if ((s.getEmpResource() != null) && (s.getEmpResource().getId() != null)) {
                    Join<CustomerResourceLine, EmpResource> empResourceJoin = root.join("empResource");
                    predicates.add(empResourceJoin.in(s.getEmpResource().getId()));
                }

                query.orderBy(cb.desc(root.get("lineId")));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });


        return customerResourceLines;
    }


}

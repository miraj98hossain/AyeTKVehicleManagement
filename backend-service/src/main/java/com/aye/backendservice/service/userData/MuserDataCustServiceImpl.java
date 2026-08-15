package com.aye.backendservice.service.userData;


import com.aye.backendservice.repository.user.CustProfileRepo;
import com.aye.backendservice.repository.userData.MuserDataCustMRepo;
import com.aye.backendservice.repository.userData.MuserDataCustRepo;
import com.aye.entity.user.CustProfile;
import com.aye.entity.user.userData.MuserDataCust;
import com.aye.entity.user.userData.MuserDataCustM;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MuserDataCustServiceImpl implements MuserDataCustService {

    @Autowired
    private MuserDataCustRepo muserDataCustrepo;

    @Autowired
    private MuserDataCustMRepo muserDataCustMrepo;

    @Autowired
    private CustProfileRepo custProfileRepo;

    @Override
    public List<MuserDataCust> getAllCustomer() {
        // TODO Auto-generated method stub
        return this.muserDataCustrepo.findAll();
    }

    @Override
    public List<MuserDataCust> getAllCustomerByOrg(Long orgId) {
        return this.muserDataCustrepo.findByOrgId(orgId);
    }

    @Override
    public List<MuserDataCust> searchMuserDataCust(MuserDataCust s) {
        List<MuserDataCust> muserDataCusts = this.muserDataCustrepo.findAll(new Specification<MuserDataCust>() {
            @Override
            public Predicate toPredicate(Root<MuserDataCust> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(cb.equal(root.get("orgId"), s.getOrgId()));
                if (s.getAutoNumber() != null) {
                    predicates.add(cb.equal(root.get("autoNumber"), s.getAutoNumber()));
                }
                if (s.getPartyId() != null) {
                    predicates.add(cb.equal(root.get("partyId"), s.getPartyId()));
                }

                if (s.getAccountNumber() != null) {
                    predicates.add(cb.equal(root.get("accountNumber"), s.getAccountNumber()));
                }

                if (s.getShiptoLoc() != null) {
                    predicates.add(cb.equal(root.get("shiptoLoc"), s.getShiptoLoc()));
                }

                if (s.getCustAcctId() != null) {
                    predicates.add(cb.equal(root.get("custAcctId"), s.getCustAcctId()));
                }

                if (s.getCustomerName() != null) {
                    predicates.add(cb.equal(root.get("customerName"), s.getCustomerName()));
                }

                if (s.getSetId() != null) {
                    predicates.add(cb.equal(root.get("setId"), s.getSetId()));
                }

                if (s.getCustAcctSiteId() != null) {
                    predicates.add(cb.equal(root.get("custAcctSiteId"), s.getCustAcctSiteId()));
                }

                if (s.getBillToId() != null) {
                    predicates.add(cb.equal(root.get("billToId"), s.getBillToId()));
                }

                if (s.getShipToId() != null) {
                    predicates.add(cb.equal(root.get("shipToId"), s.getShipToId()));
                }
                criteriaQuery.orderBy(cb.desc(root.get("id")));
                criteriaQuery.distinct(true);
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        });
        return muserDataCusts;
    }

    @Override
    public List<CustProfile> searchCustProfile(CustProfile s) {
        List<CustProfile> custProfiles = this.custProfileRepo.findAll(new Specification<CustProfile>() {
            @Override
            public Predicate toPredicate(Root<CustProfile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(cb.equal(root.get("orgId"), s.getOrgId()));

                if (s.getAccountNumber() != null) {
                    predicates.add(cb.like(root.get("accountNumber"), '%' + s.getAccountNumber() + '%'));
                }

                if (s.getCustAcctId() != null) {
                    predicates.add(cb.equal(root.get("custAcctId"), s.getCustAcctId()));
                }

                if (s.getName() != null) {
                    predicates.add(cb.like(cb.upper(root.get("name")), '%' + s.getName().toUpperCase() + '%'));
                }

                query.orderBy(cb.desc(root.get("id")));
                query.distinct(true);
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
        return custProfiles;
    }

    @Override
    public MuserDataCust getById(Integer id) {
        // TODO Auto-generated method stub

        return this.muserDataCustrepo.findById(id).orElse(null);
    }

    @Override
    public List<MuserDataCustM> findByAutonumberName(String autoNumber, String CustName) {
//		return this.muserDataCustMrepo.findByAutonumberName(autoNumber, CustName);
        return this.muserDataCustMrepo.findByAutoNumberAndCustomerNameContaining(autoNumber, CustName);
    }

//	@Override
//	public List<MuserDataCustM> findByAutonumberOrgName(String autoNumber, Long orgId, String CustName) {
//		return this.muserDataCustMrepo.findByAutoNumberAndOrgIdAndCustomerNameContaining(autoNumber,orgId,CustName);
//	};

    @Override
    public List<MuserDataCustM> findByAutonumberOrgName(String autoNumber, Long orgId, String CustName) {
        return this.muserDataCustMrepo.findByAutoNumberOrgIdCustomerName(autoNumber, orgId, CustName);
    }

    ;

    @Override
    public Integer getCustExist(Integer custAcctId, String AutoNumber, Long orgId) {
        //System.out.println("In MuserDataCustServiceImpl");
        return this.muserDataCustrepo.getCustExist(custAcctId, AutoNumber, orgId);
    }

    @Override
    public Integer getShipto(Integer custAcctId, String AutoNumber, Long orgId) {
        // TODO Auto-generated method stub
        return this.muserDataCustrepo.getShipto(custAcctId, AutoNumber, orgId);
    }

    @Override
    public Integer getBillto(Integer custAcctId, String AutoNumber, Long orgId) {

        return this.muserDataCustrepo.getBillto(custAcctId, AutoNumber, orgId);
    }

    @Override
    public List<MuserDataCustM> getByAutoNumber(String AutoNumber) {

        List<MuserDataCustM> custM = this.muserDataCustMrepo.getByAutonumber(AutoNumber);

        return custM;
    }

}

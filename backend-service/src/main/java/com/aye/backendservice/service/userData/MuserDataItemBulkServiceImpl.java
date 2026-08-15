package com.aye.backendservice.service.userData;


import com.aye.backendservice.repository.userData.MuserDataItemBulkRepo;
import com.aye.dto.response.order.BulkItemSearchResDto;
import com.aye.entity.user.userData.MuserDataItemBulk;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Toufiq on 7/11/2019.
 */
@Service
public class MuserDataItemBulkServiceImpl implements MuserDataItemBulkService {
    @Autowired
    private MuserDataItemBulkRepo muserDataItemBulkRepo;

    @Override
    public Page<MuserDataItemBulk> getItemByOrgInvOrgTrnsTypeItemMusk(Long orgId, Long invOrgId, Integer transactionTypeId, String itemMask, Pageable pageable) {
        return this.muserDataItemBulkRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndItemMaskContaining(orgId, invOrgId,
                transactionTypeId, itemMask, pageable);
    }

    @Override
    public List<MuserDataItemBulk> getItemByInvOrgIdAndTransactionTypeId(Long invOrgId, Integer transactionTypeId) {
        return this.muserDataItemBulkRepo.findByInvOrgIdAndTransactionTypeId(invOrgId, transactionTypeId);
    }

    @Override
    public List<MuserDataItemBulk> getItemByName(Long orgId, Long invOrgId, Integer transactionTypeId, String itemMask) {
//        String itm =itemMask.toUpperCase();
        return this.muserDataItemBulkRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndStatusAndItemMaskContaining(orgId, invOrgId, transactionTypeId, "A", itemMask.toUpperCase());
    }

    @Override
    public MuserDataItemBulk getItemByOrgIdAndId(Long orgId, Integer id) {
        return this.muserDataItemBulkRepo.findByOrgIdAndId(orgId, id);
    }


    @Override
    public Integer getItemExist(Integer invItemId, Long invOrgId) {
        return this.muserDataItemBulkRepo.countByInvOrgIdAndInvItemId(invOrgId, invItemId);
    }

    @Override
    public Integer getBulkOrderType(Long invOrgId) {
        return this.muserDataItemBulkRepo.getBulkOrderType(invOrgId);
    }

    @Override
    public List<BulkItemSearchResDto> getSearchItemByName(Long orgId, Long invOrgId, String itemMask) {

        List<BulkItemSearchResDto> collect = this.muserDataItemBulkRepo.findAll(new Specification<MuserDataItemBulk>() {
            @Override
            public Predicate toPredicate(Root<MuserDataItemBulk> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (orgId != null) {
                    predicates.add(cb.equal(root.get("orgId"), orgId));
                }
                if (invOrgId != null) {
                    predicates.add(cb.equal(root.get("invOrgId"), invOrgId));
                }

//                if (transactionTypeId != null) {
//                    predicates.add(cb.equal(root.get("transactionTypeId"), transactionTypeId));
//                }

                if (itemMask != null) {
                    predicates.add(cb.like(cb.upper(root.get("itemMask")), '%' + itemMask.toUpperCase() + '%'));
                }
//                criteriaQuery.orderBy(cb.desc(root.get("id")));
//                criteriaQuery.distinct(true);
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        }).stream().map(m -> {
            return new BulkItemSearchResDto(m.getOrgId(), m.getInvOrgId(), m.getInvItemId(), m.getItemCode(), m.getItemMask());
        }).collect(Collectors.toList());
        return collect;
//        return this.muserDataItemBulkRepo.findOrgIdByOrgId(orgId);
    }
}

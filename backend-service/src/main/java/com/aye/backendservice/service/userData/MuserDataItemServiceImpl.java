package com.aye.backendservice.service.userData;


import com.aye.backendservice.repository.userData.MitemCatVRepo;
import com.aye.backendservice.repository.userData.MuserDataItemRepo;
import com.aye.dto.response.order.OrderType;
import com.aye.entity.MitemCatV;
import com.aye.entity.user.userData.MhierarchyCategoryV;
import com.aye.entity.user.userData.MuserDataItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MuserDataItemServiceImpl implements MuserDataItemService {

    @PersistenceContext

    private EntityManager em;

    @Autowired
    private MuserDataItemRepo muserDataItemRepo;

    @Autowired
    private MitemCatVRepo mitemCatVRepo;


    @Override
    public List<MuserDataItem> getAllItem(Long Orgid, Long invOrgId, Integer transactionTypeId) {
        // TODO Auto-generated method stub
        return this.muserDataItemRepo.getItem(Orgid, invOrgId, transactionTypeId);
    }

    @Override
    public List<MuserDataItem> getItembyName(Long Orgid,
                                             Long invOrgId, Integer transactionTypeId, String itemName) {
        return this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndPrimarySalesFlagAndItemMaskContainingOrderByItemMask(Orgid, invOrgId, transactionTypeId, "Y", itemName);
    }

    @Override
    public List<MuserDataItem> getItembyNameDim(Long Orgid,
                                                Long invOrgId, Integer transactionTypeId, String itemName) {
        return this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndDiamentionalItemAndPrimarySalesFlagAndItemMaskContainingOrderByItemMask(Orgid, invOrgId, transactionTypeId, "Y", "Y", itemName);
    }


    public List<MuserDataItem> getItembyName(Long Orgid,
                                             Long invOrgId, Integer transactionTypeId, String itemName, Integer userId) {
        return this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndPrimarySalesFlagAndItemMaskContainingOrderByItemMask(Orgid, invOrgId, transactionTypeId, "Y", itemName);
    }

    @Override
    public List<MuserDataItem> getItembyNameAndHierarchyCategries(Long Orgid,
                                                                  Long invOrgId, Integer transactionTypeId, String itemName, List<MhierarchyCategoryV> categoryVList) {
        String categoryType = categoryVList.stream().distinct().map(m -> m.getCategoryType()).findFirst().get();
        List<String> categoryvals = categoryVList.stream().map(m -> m.getCategoryValue()).collect(Collectors.toList());
//		System.out.println(categoryType);
//		System.out.println(categoryvals);
        if (categoryType.equals(("ITEM CLASS"))) {
            List<MuserDataItem> mU = this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndPrimarySalesFlagAndItemMaskContainingAndItemClassInOrderByItemMask(Orgid, invOrgId, transactionTypeId, "Y", itemName, categoryvals);
            return mU;
//				return this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndPrimarySalesFlagAndItemMaskContainingAndItemClassInOrderByItemMask(Orgid, invOrgId, transactionTypeId, "Y", itemName, categoryvals);
        } else {
            List<MuserDataItem> mU = this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndPrimarySalesFlagAndItemMaskContainingOrderByItemMask(Orgid, invOrgId, transactionTypeId, "Y", itemName);
            return mU;
//				return this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndPrimarySalesFlagAndItemMaskContainingOrderByItemMask(Orgid, invOrgId, transactionTypeId, "Y", itemName);
        }

    }


    @Override
    public List<MuserDataItem> getItembyNameAndHierarchyCategriesDim(Long Orgid,
                                                                     Long invOrgId, Integer transactionTypeId, String itemName, List<MhierarchyCategoryV> categoryVList) {
        String categoryType = categoryVList.stream().distinct().map(m -> m.getCategoryType()).findFirst().get();
        List<String> categoryvals = categoryVList.stream().map(m -> m.getCategoryValue()).collect(Collectors.toList());
//		System.out.println(categoryType);
//		System.out.println(categoryvals);
        if (categoryType.equals(("ITEM CLASS"))) {
            List<MuserDataItem> mU = this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndDiamentionalItemAndPrimarySalesFlagAndItemMaskContainingAndItemClassInOrderByItemMask(Orgid, invOrgId, transactionTypeId, "Y", "Y", itemName, categoryvals);
            return mU;
//				return this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndPrimarySalesFlagAndItemMaskContainingAndItemClassInOrderByItemMask(Orgid, invOrgId, transactionTypeId, "Y", itemName, categoryvals);
        } else {
            List<MuserDataItem> mU = this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndDiamentionalItemAndPrimarySalesFlagAndItemMaskContainingOrderByItemMask(Orgid, invOrgId, transactionTypeId, "Y", "Y", itemName);
            return mU;
//				return this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndPrimarySalesFlagAndItemMaskContainingOrderByItemMask(Orgid, invOrgId, transactionTypeId, "Y", itemName);
        }

    }


    @Override
    public List<String> getItemClassbyHierarchyCategries(Long Orgid,
                                                         Long invOrgId, String itemClass, List<MhierarchyCategoryV> categoryVList) {
        String categoryType = categoryVList.stream().distinct().map(m -> m.getCategoryType()).findFirst().get();
        List<String> categoryvals = categoryVList.stream().map(m -> m.getCategoryValue()).collect(Collectors.toList());
//		System.out.println(categoryType);
//		System.out.println(categoryvals);
        if (categoryType.equals(("ITEM CLASS"))) {
            List<MitemCatV> mUclass = this.mitemCatVRepo.findByOrgIdAndInvOrgIdAndClassNameContainingAndItemClassInOrderByItemClass(Orgid, invOrgId, itemClass, categoryvals);

            List<String> distinctCLass = mUclass.stream().map(MitemCatV::getItemClass).distinct().collect(Collectors.toList());

            return distinctCLass;
//				return this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndPrimarySalesFlagAndItemMaskContainingAndItemClassInOrderByItemMask(Orgid, invOrgId, transactionTypeId, "Y", itemName, categoryvals);
        } else {
            return null;
        }

    }


    @Override
    public List<MuserDataItem> getItembyNameStock(Long Orgid,
                                                  Long invOrgId, Integer transactionTypeId, String itemName) {
        List<MuserDataItem> muserDataItems = this.muserDataItemRepo.findAll(new Specification<MuserDataItem>() {
            @Override
            public Predicate toPredicate(Root<MuserDataItem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(cb.equal(root.get("orgId"), Orgid));
                predicates.add(cb.equal(root.get("transactionTypeId"), transactionTypeId));
                predicates.add(cb.like(cb.upper(root.get("itemMask")), '%' + itemName.toUpperCase() + '%'));
                predicates.add(cb.equal(root.get("uomCode"), root.get("primaryUomCode")));
                criteriaQuery.orderBy(cb.desc(root.get("itemMask")));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        });
        return muserDataItems;
//		return this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndItemMaskContainingOrderByItemMask(Orgid, invOrgId, transactionTypeId,itemName);
//		return this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndTransactionTypeIdAndPrimarySalesFlagNotAndItemMaskContainingOrderByItemMask(Orgid, invOrgId, transactionTypeId, "Y", itemName);
//		return this.muserDataItemRepo.findItemForStockByItemMask(Orgid, invOrgId, transactionTypeId,itemName);
    }

    @Override
    public MuserDataItem getItemById(Integer id) {
        // TODO Auto-generated method stub
        return this.muserDataItemRepo.getOne(id);
    }

    @Override
    public MuserDataItem findByOrgIdAndInvOrgIdAndInvItemIdAndUomCode(Long Orgid, Long InvOrg, Integer invItemId, String uomCode) {
        return this.muserDataItemRepo.findByOrgIdAndInvOrgIdAndInvItemIdAndUomCode(Orgid, InvOrg, invItemId, uomCode);
    }

    @Override
    public Integer getItemExist(Integer invItemId, Long invOrgId) {
        // TODO Auto-generated method stub
        return this.muserDataItemRepo.getItemExist(invItemId, invOrgId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<OrderType> getOrderType(Long invOrgId) {


        Query Qr = this.em.createNativeQuery("Select Distinct TRANSACTION_TYPE_ID,NAME,ORGANIZATION_ID from M_USERDATA_ITEM \r\n" +
                " where ORGANIZATION_ID=:P_ORGANIZATION_ID").setParameter("P_ORGANIZATION_ID", invOrgId);

        List<Object[]> rows = Qr.getResultList();

        List<OrderType> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new OrderType((Integer) row[0], (String) row[1], (Integer) row[2]));
        }
        return result;
    }

    @Override
    public Integer getOrderTypeExist(Long invOrgId, Integer transactionTypeId) {

        return this.muserDataItemRepo.getOrderType(invOrgId, transactionTypeId);
    }

    @Override
    public Integer getPriceList(Integer invItemId, String Uom, Long invOrgId, Integer transactionTypeId) {
        // TODO Auto-generated method stub
        return this.muserDataItemRepo.getPriceList(invItemId, Uom, invOrgId, transactionTypeId);
    }

    @Override
    public Integer getItemUomExist(Integer invItemId, String Uom, Long invOrgId) {

        return this.muserDataItemRepo.getItemUomExist(invItemId, Uom, invOrgId);
    }

    @Override
    public BigDecimal getItemPrice(Integer invItemId, String Uom, Long invOrgId, Integer transactionTypeId) {
        // TODO Auto-generated method stub
        return this.muserDataItemRepo.getItemPrice(invItemId, Uom, invOrgId, transactionTypeId);
    }


    @Override
    public List<MuserDataItem> get10Item(Long Orgid, Long
            invOrgId, Integer transactionTypeId, Integer fIndex, Integer tIndex) {
        List<MuserDataItem> item = new ArrayList<>();
        item = this.muserDataItemRepo.get10item(Orgid, invOrgId, transactionTypeId, fIndex, tIndex);
        return item;
    }


    public List<MuserDataItem> get10Item(Long Orgid, Long
            invOrgId, Integer transactionTypeId, Integer userId, Integer fIndex, Integer tIndex) {
        //find user
        //fidn emp
        //find hierarchy
        //

        List<MuserDataItem> item = new ArrayList<>();
        item = this.muserDataItemRepo.get10item(Orgid, invOrgId, transactionTypeId, fIndex, tIndex);
        return item;
    }


    @Override
    public Page<MuserDataItem> get10Item(Long Orgid, Long
            invOrgId, Integer transactionTypeId, Pageable p) {

        Page<MuserDataItem> muserDataItems = this.muserDataItemRepo.findAll(new Specification<MuserDataItem>() {
            @Override
            public Predicate toPredicate(Root<MuserDataItem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.get("orgId"), Orgid));
                predicates.add(cb.equal(root.get("invOrgId"), invOrgId));

                if (transactionTypeId != null) {
                    predicates.add(cb.equal(root.get("transactionTypeId"), transactionTypeId));
                }
                criteriaQuery.orderBy(cb.desc(root.get("id")));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, p);
        return muserDataItems;
    }

    @Override
    public MuserDataItem getItemByinvOrgAndPrimarySalesFlag(Long InvOrg, Integer invItemId) {
        return this.muserDataItemRepo.findByInvOrgIdAndInvItemIdAndPrimarySalesFlag(InvOrg, invItemId, "Y");
    }


    @Override
    public BigDecimal getConvertQty(Integer p_inventory_item_id,
                                    BigDecimal p_qty,
                                    String p_from_uom,
                                    String p_to_uom) {
        return this.muserDataItemRepo.uomConvert(p_inventory_item_id, p_qty, p_from_uom, p_to_uom);

    }

    @Override
    public List<MuserDataItem.ItemInfo> findByOrgId(Long Orgid) {
        return this.muserDataItemRepo.findDistinctOrgIdAndInvItemIdAndItemCodeAndItemMaskByOrgId(Orgid);
    }


}

package com.aye.backendservice.service.userData;


import com.aye.dto.response.order.OrderType;
import com.aye.entity.user.userData.MhierarchyCategoryV;
import com.aye.entity.user.userData.MuserDataItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface MuserDataItemService {
    List<MuserDataItem> getAllItem(Long OrgId, Long invOrgId, Integer transactionTypeId);

    List<MuserDataItem> getItembyName(Long OrgId, Long invOrgId, Integer transactionTypeId, String itemName);

    List<MuserDataItem> getItembyNameDim(Long OrgId, Long invOrgId, Integer transactionTypeId, String itemName);

    List<MuserDataItem> getItembyNameAndHierarchyCategries(Long OrgId,
                                                           Long invOrgId, Integer transactionTypeId, String itemName, List<MhierarchyCategoryV> categoryVList);


    List<MuserDataItem> getItembyNameAndHierarchyCategriesDim(Long OrgId,
                                                              Long invOrgId, Integer transactionTypeId, String itemName, List<MhierarchyCategoryV> categoryVList);


    List<String> getItemClassbyHierarchyCategries(Long Orgid,
                                                  Long invOrgId, String itemClass, List<MhierarchyCategoryV> categoryVList);

    List<MuserDataItem> getItembyNameStock(Long OrgId, Long invOrgId, Integer transactionTypeId, String itemName);

    MuserDataItem getItemById(Integer id);

    MuserDataItem findByOrgIdAndInvOrgIdAndInvItemIdAndUomCode(Long OrgId, Long InvOrg, Integer invItemId, String uomCode);

    Integer getItemExist(Integer invItemId, Long invOrgId);

    List<OrderType> getOrderType(Long invOrgId);

    Integer getOrderTypeExist(Long invOrgId, Integer transactionTypeId);

    Integer getPriceList(Integer invItemId, String Uom, Long invOrgId, Integer transactionTypeId);

    Integer getItemUomExist(Integer invItemId, String Uom, Long invOrgId);

    BigDecimal getItemPrice(Integer invItemId, String Uom, Long invOrgId, Integer transactionTypeId);

    List<MuserDataItem> get10Item(Long OrgId, Long invOrgId, Integer transactionTypeId, Integer fIndex, Integer tIndex);

    Page<MuserDataItem> get10Item(Long OrgId, Long invOrgId, Integer transactionTypeId, Pageable p);

    MuserDataItem getItemByinvOrgAndPrimarySalesFlag(Long InvOrg, Integer invItemId);

    BigDecimal getConvertQty(Integer p_inventory_item_id,
                             BigDecimal p_qty,
                             String p_from_uom,
                             String p_to_uom);

    List<MuserDataItem.ItemInfo> findByOrgId(Long OrgId);
}

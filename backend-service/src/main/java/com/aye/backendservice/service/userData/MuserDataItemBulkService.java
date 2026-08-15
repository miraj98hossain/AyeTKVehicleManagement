package com.aye.backendservice.service.userData;


import com.aye.dto.response.order.BulkItemSearchResDto;
import com.aye.entity.user.userData.MuserDataItemBulk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MuserDataItemBulkService {
    Page<MuserDataItemBulk> getItemByOrgInvOrgTrnsTypeItemMusk(Long orgId, Long invOrgId, Integer transactionTypeId, String itemMask, Pageable pageable);

    List<MuserDataItemBulk> getItemByInvOrgIdAndTransactionTypeId(Long invOrgId, Integer transactionTypeId);

    List<MuserDataItemBulk> getItemByName(Long orgId, Long invOrgId, Integer transactionTypeId, String itemMask);

    List<BulkItemSearchResDto> getSearchItemByName(Long orgId, Long invOrgId, String itemMask);

    MuserDataItemBulk getItemByOrgIdAndId(Long orgId, Integer id);

    Integer getItemExist(Integer invItemId, Long invOrgId);

    Integer getBulkOrderType(Long invOrgId);

//
//	public List<MuserDataItem> getAllItem(Long orgId, Long invOrgId, Integer transactionTypeId);
//	public List<MuserDataItem> getItembyName(Long orgId, Long invOrgId, Integer transactionTypeId, String itemName);
//	public MuserDataItem getItemById(Integer id);
////	public Integer getItemExist(Integer invItemId, Long invOrgId);
//	public List<OrderType> getOrderType(Long invOrgId);
//	public Integer getOrderTypeExist(Long invOrgId);
//	public Integer getPriceList(Integer invItemId, String Uom,	 Long invOrgId);
//	public Integer getItemUomExist(Integer invItemId, String Uom, Long invOrgId);
//	public Double getItemPrice(Integer invItemId, String Uom, Long invOrgId);
//	public List<MuserDataItem> get10Item(Long orgId, Long invOrgId, Integer transactionTypeId, Integer fIndex, Integer tIndex);

}

package com.aye.backendservice.repository.userData;

//import java.math.BigDecimal;


import com.aye.entity.user.userData.MuserDataItemBulk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MuserDataItemBulkRepo extends JpaRepository<MuserDataItemBulk, Integer>, JpaSpecificationExecutor<MuserDataItemBulk> {

    //	@Query("select item from MuserDataItem item where "
//			+ "item.OrgId=:OrgId and item.InvOrgId=:invOrgId and TransactionTypeId = nvl(:transactionTypeId,item.TransactionTypeId) ")
//	List<MuserDataItem> getItem(@Param("OrgId") Long orgId, @Param("invOrgId") Long invOrgId, @Param("transactionTypeId") Integer transactionTypeId);
    Page<MuserDataItemBulk> findByOrgIdAndInvOrgIdAndTransactionTypeIdAndItemMaskContaining(Long orgId, Long invOrgId,
                                                                                            Integer transactionTypeId, String itemMask, Pageable pageable);

    List<MuserDataItemBulk> findByInvOrgIdAndTransactionTypeId(Long invOrgId, Integer transactionTypeId);

    List<MuserDataItemBulk> findByOrgIdAndInvOrgIdAndTransactionTypeIdAndItemMaskContaining(Long orgId, Long invOrgId, Integer transactionTypeId, String itemMask);

    List<MuserDataItemBulk> findByOrgIdAndInvOrgIdAndTransactionTypeIdAndStatusAndItemMaskContaining(Long orgId, Long invOrgId, Integer transactionTypeId, String status, String itemMask);

    MuserDataItemBulk findByOrgIdAndId(Long orgId, Integer id);

    Integer countByInvOrgIdAndInvItemId(Long invOrgId, Integer invItemId);

    @Query("select distinct item.transactionTypeId from MuserDataItemBulk item where "
            + "item.invOrgId=:invOrgId ")
    Integer getBulkOrderType(@Param("invOrgId") Long invOrgId);

//	Distinct findOrgIdAndInvOrgIdAndInvItemIdAndTransactionTypeIdAndItemCodeAndItemMaskByOrgIdAndInvOrgIdAndTransactionTypeIdAndItemMaskContaining(Long orgId, Long invOrgId,Integer transactionTypeId, String itemMask);


//	List<BulkItemSearchResponse> findOrgIdByOrgId(Long orgId);
//@Query(value = "SELECT DISTINCT m.org_id AS orgId FROM m_userdata_Item_BULK m WHERE m.org_id = :orgId",nativeQuery = true)
//List<MuserDataItemBulk.BulkItemInfo> findDistinctOrgIdByOrgId(@Param("orgId") Long orgId);

//	@Query(" select item from MuserDataItem item where "
//			+ "item.OrgId=:OrgId and item.InvOrgId=:invOrgId and TransactionTypeId = nvl(:transactionTypeId,item.TransactionTypeId) "
//			+ "and item.ItemMask like :itemName||'%' ")
//	List<MuserDataItem> getItembyName(@Param("OrgId") Long orgId, @Param("invOrgId") Long invOrgId,
//									  @Param("transactionTypeId") Integer transactionTypeId, @Param("itemName") String itemName);


//	@Query("select count(item) from MuserDataItem item where "
//			+ "item.InvOrgId=:invOrgId and item.InvItemId=:invItemId")
//	Integer getItemExist(@Param("invItemId") Integer invItemId, @Param("invOrgId") Long invOrgId);


//	@Query("select distinct item.PriceListHeadarId from MuserDataItem item where "
//			+ "item.InvOrgId=:invOrgId and item.UomCode=:Uom and item.InvItemId=:invItemId")
//	Integer getPriceList(@Param("invItemId") Integer invItemId, @Param("Uom") String Uom, @Param("invOrgId") Long invOrgId);

//	@Query("select count(item) from MuserDataItem item where "
//			+ "item.InvOrgId=:invOrgId and item.UomCode=:Uom and item.InvItemId=:invItemId")
//	Integer getItemUomExist(@Param("invItemId") Integer invItemId, @Param("Uom") String Uom, @Param("invOrgId") Long invOrgId);


//	@Query("select item.ItemPrice from MuserDataItem item where "
//			+ "item.InvOrgId=:invOrgId and item.UomCode=:Uom and item.InvItemId=:invItemId")
//	Double getItemPrice(@Param("invItemId") Integer invItemId, @Param("Uom") String Uom, @Param("invOrgId") Long invOrgId);


//	@Query(value="SELECT r_id, M.ID, M.INVENTORY_ITEM_ID, M.ORGANIZATION_ID, \r\n" +
//	"   M.ITEM_CODE, M.ITEM_DESC, M.ITEM_MASK, \r\n" +
//	"   M.ORG_ID, M.STATUS, M.UOM, \r\n" +
//	"   M.PRICE, M.LIST_HEADER_ID, M.TRANSACTION_TYPE_ID, \r\n" +
//	"   M.NAME\r\n" +
//	"  FROM (\r\n" +
//	"  SELECT ROWNUM r_id, x.*\r\n" +
//	"          FROM M_USERDATA_ITEM x\r\n"
//	+ "where x.ORG_ID=:Orgid and x.ORGANIZATION_ID=:invOrgId and TRANSACTION_TYPE_ID = nvl(:transactionTypeId,x.TRANSACTION_TYPE_ID)" +
//	"          ) M\r\n" +
//	" WHERE r_id BETWEEN :fRow AND :tRow",nativeQuery = true)
//	List<MuserDataItem> get10item(@Param("Orgid") Long orgId, @Param("invOrgId") Long invOrgId,
//								  @Param("transactionTypeId") Integer transactionTypeId,
//								  @Param("fRow") Integer fRow, @Param("tRow") Integer tRow);


}

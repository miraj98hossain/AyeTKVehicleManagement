package com.aye.backendservice.repository.userData;

//import java.math.BigDecimal;


import com.aye.entity.user.userData.MuserDataItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface MuserDataItemRepo extends JpaRepository<MuserDataItem, Integer>, JpaSpecificationExecutor<MuserDataItem> {

//    @Query(value = "select item from MuserDataItem item where "
//            + "item.orgId=:OrgId and item.invOrgId=:invOrgId and item.transactionTypeId= nvl(:transactionTypeId,item.transactionTypeId) and item.primarySalesFlag='Y' ", nativeQuery = true)
//

    /// /    @Query(value = "select * from m_userdata_Item item where "
    /// /            + "ORG_ID=:OrgId and ORGANIZATION_ID=:invOrgId and TRANSACTION_TYPE_ID= nvl(:transactionTypeId,item.transactionTypeId) and PRIMARY_SALES_FLAG='Y' ", nativeQuery = true)
//    List<MuserDataItem> getItem(@Param("OrgId") Long OrgId, @Param("invOrgId") Long invOrgId, @Param("transactionTypeId") Integer transactionTypeId);
    @Query("SELECT i FROM MuserDataItem i WHERE "
            + "i.orgId = :OrgId "
            + "AND i.invOrgId = :invOrgId "
            + "AND (:transactionTypeId IS NULL OR i.transactionTypeId = :transactionTypeId) "
            + "AND i.primarySalesFlag = 'Y'")
    List<MuserDataItem> getItem(@Param("OrgId") Long OrgId, @Param("invOrgId") Long invOrgId, @Param("transactionTypeId") Integer transactionTypeId);

    @Query(value = " select item from MuserDataItem item where "
            + "item.orgId=:OrgId and item.invOrgId=:invOrgId and item.transactionTypeId = nvl(:transactionTypeId,item.transactionTypeId) "
            + "and item.itemMask like concat(:itemName,'%') ", nativeQuery = true)
    List<MuserDataItem> getItembyName(@Param("OrgId") Long OrgId, @Param("invOrgId") Long invOrgId,
                                      @Param("transactionTypeId") Integer transactionTypeId, @Param("itemName") String itemName);

    List<MuserDataItem> findByOrgIdAndInvOrgIdAndTransactionTypeIdAndPrimarySalesFlagAndItemMaskContainingOrderByItemMask(Long OrgId, Long invOrgId,
                                                                                                                          Integer transactionTypeId, String f, String itemMask);

    List<MuserDataItem> findByOrgIdAndInvOrgIdAndTransactionTypeIdAndItemMaskContainingOrderByItemMask(Long OrgId, Long invOrgId,
                                                                                                       Integer transactionTypeId, String itemMask);

    List<MuserDataItem> findByOrgIdAndInvOrgIdAndTransactionTypeIdAndDiamentionalItemAndPrimarySalesFlagAndItemMaskContainingOrderByItemMask(Long OrgId, Long invOrgId,
                                                                                                                                             Integer transactionTypeId, String diamentionalItem, String f, String itemMask);


    List<MuserDataItem> findByOrgIdAndInvOrgIdAndTransactionTypeIdAndPrimarySalesFlagNotAndItemMaskContainingOrderByItemMask(Long OrgId, Long invOrgId,
                                                                                                                             Integer transactionTypeId, String pSalesFlg, String itemMask);

    @Query(value = "select a from MuserDataItem a where a.orgId= :OrgId and a.invOrgId=:invOrgId and a.transactionTypeId=:transactionTypeId and a.itemMask like '%'+:itemMask+'%' and a.uomCode=a.primaryUomCode ", nativeQuery = true)
    List<MuserDataItem> findItemForStockByItemMask(@Param("OrgId") Long OrgId, @Param("invOrgId") Long invOrgId,
                                                   @Param("transactionTypeId") Integer transactionTypeId, @Param("itemMask") String itemMask);


    @Query(value = "select count(item) from MuserDataItem item where "
            + "item.invOrgId=:invOrgId and item.invItemId=:invItemId", nativeQuery = true)
    Integer getItemExist(@Param("invItemId") Integer invItemId, @Param("invOrgId") Long invOrgId);

    @Query(value = "select distinct item.transactionTypeId from MuserDataItem item where "
            + "item.invOrgId=:invOrgId and item.transactionTypeId=:transactionTypeId", nativeQuery = true)
    Integer getOrderType(@Param("invOrgId") Long invOrgId, @Param("transactionTypeId") Integer transactionTypeId);

    @Query(value = "select distinct item.priceListHeadarId from MuserDataItem item where "
            + "item.invOrgId=:invOrgId and item.uomCode=:Uom and item.invItemId=:invItemId and item.transactionTypeId=:transactionTypeId", nativeQuery = true)
    Integer getPriceList(@Param("invItemId") Integer invItemId, @Param("Uom") String Uom, @Param("invOrgId") Long invOrgId, @Param("transactionTypeId") Integer transactionTypeId);

    @Query(value = "select count(item) from MuserDataItem item where "
            + "item.invOrgId=:invOrgId and item.uomCode=:Uom and item.invItemId=:invItemId", nativeQuery = true)
    Integer getItemUomExist(@Param("invItemId") Integer invItemId, @Param("Uom") String Uom, @Param("invOrgId") Long invOrgId);


    @Query(value = "select item.itemPrice from MuserDataItem item where "
            + "item.invOrgId=:invOrgId and item.uomCode=:Uom and item.invItemId=:invItemId and item.transactionTypeId=:transactionTypeId", nativeQuery = true)
    BigDecimal getItemPrice(@Param("invItemId") Integer invItemId, @Param("Uom") String Uom, @Param("invOrgId") Long invOrgId, @Param("transactionTypeId") Integer transactionTypeId);

    @Query(value = "SELECT r_id, M.ID, M.INVENTORY_ITEM_ID, M.ORGANIZATION_ID, \r\n" +
            "   M.ITEM_CODE, M.ITEM_DESC, M.ITEM_MASK, \r\n" +
            "   M.ORG_ID, M.STATUS, M.UOM, \r\n" +
            "   M.PRICE, M.LIST_HEADER_ID, M.TRANSACTION_TYPE_ID, \r\n" +
            "   M.NAME\r\n" +
            "  FROM (\r\n" +
            "  SELECT ROWNUM r_id, x.*\r\n" +
            "          FROM M_USERDATA_ITEM x\r\n"
            + "where x.ORG_ID=:Orgid and x.ORGANIZATION_ID=:invOrgId and TRANSACTION_TYPE_ID = nvl(:transactionTypeId,x.TRANSACTION_TYPE_ID) and PRIMARY_SALES_FLAG='Y'" +
            "          ) M\r\n" +
            " WHERE r_id BETWEEN :fRow AND :tRow", nativeQuery = true)
    List<MuserDataItem> get10item(@Param("Orgid") Long OrgId, @Param("invOrgId") Long invOrgId,
                                  @Param("transactionTypeId") Integer transactionTypeId,
                                  @Param("fRow") Integer fRow, @Param("tRow") Integer tRow);


    MuserDataItem findByOrgIdAndInvOrgIdAndInvItemIdAndUomCode(Long OrgId, Long InvOrg, Integer invItemId, String uomCode);

    MuserDataItem findByOrgIdAndInvOrgIdAndInvItemIdAndUomCodeAndTransactionTypeId(Long OrgId, Long InvOrg, Integer invItemId, String uomCode, Integer transactionTypeId);

    MuserDataItem findByInvOrgIdAndInvItemId(Long InvOrg, Integer invItemId);

    MuserDataItem findByInvOrgIdAndInvItemIdAndPrimarySalesFlag(Long InvOrg, Integer invItemId, String p);

    //	@Procedure(procedureName = "M_uomConvert")
//	BigDecimal uomConvert(Integer p_inventory_item_id,
//						  BigDecimal p_qty,
//						  String p_from_uom,
//						  String p_to_uom);
    @Query(nativeQuery = true, value = "select M_uomConvert (:p_inventory_item_id,:p_qty,:p_from_uom,:p_to_uom) from dual")
    BigDecimal uomConvert(@Param("p_inventory_item_id") Integer p_inventory_item_id,
                          @Param("p_qty") BigDecimal p_qty,
                          @Param("p_from_uom") String p_from_uom,
                          @Param("p_to_uom") String p_to_uom);

    List<MuserDataItem.ItemInfo> findDistinctOrgIdAndInvItemIdAndItemCodeAndItemMaskByOrgId(Long OrgId);

    List<MuserDataItem> findByOrgIdAndInvOrgIdAndTransactionTypeIdAndPrimarySalesFlagAndItemMaskContainingAndItemClassInOrderByItemMask(Long OrgId, Long invOrgId, Integer transactionTypeId, String y, String itemName, List<String> categoryvals);

    List<MuserDataItem> findByOrgIdAndInvOrgIdAndTransactionTypeIdAndDiamentionalItemAndPrimarySalesFlagAndItemMaskContainingAndItemClassInOrderByItemMask(Long OrgId, Long invOrgId, Integer transactionTypeId, String dy, String y, String itemName, List<String> categoryvals);

    List<MuserDataItem> findByOrgIdAndInvOrgIdAndPrimarySalesFlagAndItemClassInOrderByItemClass(Long OrgId, Long invOrgId, String y, List<String> categoryvals);

    List<MuserDataItem> findByOrgIdAndInvOrgIdAndPrimarySalesFlagAndItemClassOrderByItemClass(Long OrgId, Long invOrgId, String f, String itemClass);
}

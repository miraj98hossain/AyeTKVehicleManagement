package com.aye.backendservice.postgresEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.sql.Date;


/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 11:17
 * @project: AyeTKVehicleManagement
 */
@Table(name = "XXTKG_BEFORE_TRIP_VHCL")
@Entity
@Getter
@NoArgsConstructor
@Immutable
public class BeforeTripOrder {
    @Id
    @Column(name = "LINE_ID", nullable = false)
    private Long lineId;
    @Column(name = "HEADER_ID", nullable = false)
    private Long headerId;
    @Column(name = "ORG_ID")
    private Long orgId;
    @Column(name = "ORDER_NUMBER", nullable = false)
    private Long orderNumber;
    //    @Column(name = "ORDER_NUMBER_MODE", length = 281)
//    private String orderNumberMode;
    @Column(name = "ORDER_MODE")
    private String orderMode;
    @Column(name = "NAME", nullable = false, length = 30)
    private String name;
    @Column(name = "ORDERED_DATE")
    private Date orderedDate;
    @Column(name = "ORDERED_ITEM", length = 2000)
    private String orderedItem;
    @Column(name = "INVENTORY_ITEM_ID", nullable = false)
    private Long inventoryItemId;
    @Column(name = "ORDERED_QUANTITY")
    private BigDecimal orderedQuantity;
    @Column(name = "FLOW_STATUS_CODE", length = 30)
    private String flowStatusCode;
    @Column(name = "PARTY_NAME", nullable = false, length = 360)
    private String partyName;
    @Column(name = "CUST_ACCOUNT_ID", nullable = false, precision = 15, scale = 0)
    private Long custAccountId;
    @Column(name = "SUBINVENTORY", length = 10)
    private String subinventory;
    @Column(name = "PRICING_QUANTITY_UOM", length = 3)
    private String pricingQuantityUom;
    @Column(name = "ORDER_QUANTITY_UOM", length = 3)
    private String orderQuantityUom;
    @Column(name = "INV_ORG_ID")
    private Long invOrgId;
}

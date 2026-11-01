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
 * @time: 10:31
 * @project: AyeTKVehicleManagement
 */
@Table(name = "XXTKG_BEFORE_TRIP_W_DS_VHCL")
@Entity
@Getter
@NoArgsConstructor
@Immutable
public class BeforeTripSchedule {

    @Id
    @Column(name = "PROCESS_LINE_ID")
    private Long processLineId;

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


    @Column(name = "SCHEDULE_ID")
    private Long scheduleId;

    @Column(name = "SCHEDULE_NUMBER", length = 1000)
    private String scheduleNumber;

    @Column(name = "LINE_START_DATE")
    private Date lineStartDate;

    @Column(name = "LINE_END_DATE")
    private Date lineEndDate;

    @Column(name = "LINE_STATUS", length = 10)
    private String lineStatus;

}

package com.aye.backendservice.postgresEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "M_ITEM_CATV_NW")
@Immutable
@Data
public class MItemCatVNew {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "ORG_ID")
    private Long orgId;
    @Column(name = "ORGANIZATION_ID")
    private Long organizationId;
    @Column(name = "CATEGORIES")
    private String categories;
    @Column(name = "FAMILY")
    private String family;
    @Column(name = "CLASS")
    private String clazz;
    @Column(name = "INVENTORY_ITEM_ID")
    private Long inventoryItemId;
    @Column(name = "ITEM_CODE")
    private String itemCode;
    @Column(name = "ITEM_MASK")
    private String itemMask;
    @Column(name = "CLASS_NAME")
    private String className;
}
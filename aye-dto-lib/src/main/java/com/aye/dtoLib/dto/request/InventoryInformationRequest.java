package com.aye.dtoLib.dto.request;

import lombok.Data;

@Data
public class InventoryInformationRequest {
    private Integer id;

    private String code;

    private String name;

    private String address;

    private Integer calendarHeader;


    private String costingMethod;


    private Long orgHierarchyId;
    private Long orgHierarchyCode;
    private Long orgHierarchyName;

    private Long itemOrg;
    private Integer itemOrgCode;
    private Integer itemOrgName;

    private Boolean isItemMaster;
}

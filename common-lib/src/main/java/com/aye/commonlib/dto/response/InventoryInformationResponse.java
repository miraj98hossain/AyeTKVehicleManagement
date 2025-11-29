package com.aye.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryInformationResponse {
    private Integer id;
    private String code;
    private String name;
    private String address;
    private Integer calendarHeader;
    private String costingMethod;
    private Long orgHierarchyId;
    private String orgHierarchyName;
    private String orgHierarchyCode;
    private Integer itemOrg;
    private Boolean isItemMaster;
}

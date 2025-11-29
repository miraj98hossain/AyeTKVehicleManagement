package com.aye.commonlib.dto.response;

import lombok.Data;

@Data
public class OrdTrnsTypesVResponse {

    private Integer id;
    private String typeName;
    private String description;
    private InventoryInformationResponse invOrgs;
    private OrgHierarchyResponse orgHierarchy;
}

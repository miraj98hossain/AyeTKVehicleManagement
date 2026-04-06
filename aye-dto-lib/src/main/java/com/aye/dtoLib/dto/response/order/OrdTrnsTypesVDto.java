package com.aye.dtoLib.dto.response.order;

import com.aye.dtoLib.dto.response.userOrg.InventoryInformationResponse;
import com.aye.dtoLib.dto.response.userOrg.OrgHierarchyResponse;
import lombok.Data;

@Data
public class OrdTrnsTypesVDto {

    private Integer id;
    private String typeName;
    private String description;
    private InventoryInformationResponse invOrgs;
    private OrgHierarchyResponse orgHierarchy;
}

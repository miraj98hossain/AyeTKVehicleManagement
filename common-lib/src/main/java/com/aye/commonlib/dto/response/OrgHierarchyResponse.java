package com.aye.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrgHierarchyResponse {
    private Integer id;
    private String type;
    private String name;
    private String code;
    private String address;
    private Integer parentId;
    private Integer calendarHeader;
    private Integer invCstGrp;
    private Boolean undoLog;
    private String canChangePrice;
    private Integer orgId;
    private String approveThrugh;
    private String hierarchyPath;
    private String orgCode;
    private String orgName;
    private Boolean custAuth;
    private Boolean subinvItemBase;
    private String colorSchema;
    private String colorSchemaApp;
    private String colorSchemaButton;
    private List<InventoryInformationResponse> inventoryInformationses;
}


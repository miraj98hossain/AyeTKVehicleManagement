package com.aye.dtoLib.dto.request;

import com.aye.enums.OrgType;
import lombok.Data;

@Data
public class OrgHierarchyRequest {

    private Long id;
    private OrgType type;
    private String name;
    private String code;
    private String address;
    private Long parentId;
    private String parentOrgCode;
    private Integer calendarHeader;
    private Integer invCstGrp;
    private Boolean undoLog;
    private String canChangePrice;
    private Long orgId;
    private String approveThrugh;
    private String hierarchyPath;
    private String orgCode;
    private String orgName;
    private Boolean custAuth;
    private Boolean subinvItemBase;
    private String colorSchema;
    private String colorSchemaApp;
    private String colorSchemaButton;
    private String dimOrderEnabled;
}


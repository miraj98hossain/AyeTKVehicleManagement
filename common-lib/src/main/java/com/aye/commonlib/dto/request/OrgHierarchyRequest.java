package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrgHierarchyRequest {

    private Long id;

    @NotBlank(message = "Type is required")
    @Size(max = 50, message = "Type must be up to 50 characters")
    private String type;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be up to 100 characters")
    private String name;

    @NotBlank(message = "Code is required")
    @Size(max = 50, message = "Code must be up to 50 characters")
    private String code;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must be up to 255 characters")
    private String address;

    @NotNull(message = "Parent hierarchy ID is required")
    private Long pOrgHierarchy;

    @NotNull(message = "Calendar header is required")
    private Long calendarHeader;

    @NotNull(message = "Inventory cost group ID is required")
    private Long invCstGrp;

    @NotNull(message = "Undo log flag is required")
    private Boolean undoLog;

    @NotBlank(message = "Can change price value is required")
    @Size(max = 10, message = "Can change price must be up to 10 characters")
    private String canChangePrice;

    @NotNull(message = "Organization ID is required")
    private Integer orgId;

    @NotBlank(message = "Approval through is required")
    @Size(max = 100, message = "Approval through must be up to 100 characters")
    private String approveThrugh;

    @NotBlank(message = "Hierarchy path is required")
    @Size(max = 255, message = "Hierarchy path must be up to 255 characters")
    private String hierarchyPath;

    @NotBlank(message = "Organization code is required")
    @Size(max = 50, message = "Organization code must be up to 50 characters")
    private String orgCode;

    @NotBlank(message = "Organization name is required")
    @Size(max = 100, message = "Organization name must be up to 100 characters")
    private String orgName;

    @NotNull(message = "Customer authorization flag is required")
    private Boolean custAuth;

    @NotNull(message = "Sub-inventory item base flag is required")
    private Boolean subinvItemBase;

    @NotBlank(message = "Color schema is required")
    @Size(max = 20, message = "Color schema must be up to 20 characters")
    private String colorSchema;

    @NotBlank(message = "App color schema is required")
    @Size(max = 20, message = "App color schema must be up to 20 characters")
    private String colorSchemaApp;

    @NotBlank(message = "Button color schema is required")
    @Size(max = 20, message = "Button color schema must be up to 20 characters")
    private String colorSchemaButton;
}


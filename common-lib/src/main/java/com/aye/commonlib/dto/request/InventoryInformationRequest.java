package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class InventoryInformationRequest {
    private Integer id;

    @NotBlank(message = "Inventory code is required")
    @Size(max = 50, message = "Inventory code must be up to 50 characters")
    private String code;

    @NotBlank(message = "Inventory name is required")
    @Size(max = 100, message = "Inventory name must be up to 100 characters")
    private String name;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must be up to 255 characters")
    private String address;

    @NotNull(message = "Calendar header is required")
    private Integer calendarHeader;

    @NotBlank(message = "Costing method is required")
    @Size(max = 50, message = "Costing method must be up to 50 characters")
    private String costingMethod;

    @NotNull(message = "Organization hierarchy ID is required")
    private Long orgHierarchyId;

    @NotNull(message = "Item organization is required")
    private Long itemOrg;

    @NotNull(message = "Item master indicator is required")
    private Boolean isItemMaster;
}

package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserCodeAccessRequest {

    private Long id;
    @NotNull(message = "User cannot be null")
    private Integer userId;
    @NotNull(message = "Access code type is required")
    private AccessCodeType accessCodeType;
    @NotNull(message = "Access code level is required")
    private AccessCodeLevel accessCodeLevel;

    private Long orgId;
    private Long itemCatCombId;
    private String itemCatComb;
    private Long invInfoId;
    private String invInfoCode;

    public enum AccessCodeType {
        ITEM_CATEGORIES, VEHICLE_SCALE //, BANK, SUPPLIER, CUSTOMER, SUBINVENTORY
    }

    public enum AccessCodeLevel {
        GLOBAL, ORG, INV_ORG
    }
}

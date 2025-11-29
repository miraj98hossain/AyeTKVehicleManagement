package com.aye.commonlib.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UserAccessInvOrgRequest {
    private Long id;

    @NotNull(message = "Inventory organization ID is required")
    private Long invOrgId;
    private String templtDtlName;
    @NotNull(message = "User access template detail ID is required")
    private Integer userAccessTemltDtlId;
}


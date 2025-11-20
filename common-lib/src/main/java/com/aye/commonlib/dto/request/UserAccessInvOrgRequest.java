package com.aye.commonlib.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class UserAccessInvOrgRequest {

    private Integer id;

    @NotNull(message = "Inventory organization ID is required")
    private Integer invOrgId;

    @NotNull(message = "User access template detail ID is required")
    private Integer userAccessTemltDtlId;

    @NotEmpty(message = "User transaction types cannot be empty")
    private List<@Valid UserTransactionTypesRequest> userTransactionTypes;
}


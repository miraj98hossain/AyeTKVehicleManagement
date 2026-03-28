package com.aye.dtoLib.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSubInvAccessRequest {

    private Long id;
    private String name;
    @NotNull(message = "User transaction type ID is required")
    private Long userTransactionTypeId;
    private Long invOrgId;
    @NotBlank(message = "Sub-inventory is required")
    @Size(max = 50, message = "Sub-inventory must be up to 50 characters")
    private String subInv;
}


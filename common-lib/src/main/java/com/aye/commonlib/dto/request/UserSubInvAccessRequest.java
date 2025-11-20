package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSubInvAccessRequest {

    private Integer id;
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be up to 100 characters")
    private String name;
    @NotNull(message = "User transaction type ID is required")
    private Integer userTransactionTypeId;
    @NotBlank(message = "Sub-inventory is required")
    @Size(max = 50, message = "Sub-inventory must be up to 50 characters")
    private String subInv;
}


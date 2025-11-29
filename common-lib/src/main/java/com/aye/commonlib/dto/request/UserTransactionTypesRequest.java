package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;


@Data
public class UserTransactionTypesRequest {

    private Long id;

    @NotBlank(message = "Transaction type code is required")
    @Size(max = 50, message = "Transaction type code must be up to 50 characters")
    private String trnsType;

    @NotNull(message = "Transaction type ID is required")
    private Long trnsTypeId;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be up to 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description must be up to 255 characters")
    private String description;

    @NotNull(message = "User access inventory organization ID is required")
    private Long userAccessInvOrgId;

    @NotNull(message = "Start date is required")
    private Date startDate;

    private Date endDate;

}


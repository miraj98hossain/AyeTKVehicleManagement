package com.aye.commonlib.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class UserTransactionTypesRequest {

    private Integer id;

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
    private Integer userAccessInvOrgId;

    @NotBlank(message = "Start date is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Start date must be in format yyyy-MM-dd")
    private String startDate;

    @NotBlank(message = "End date is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "End date must be in format yyyy-MM-dd")
    private String endDate;

    @NotEmpty(message = "User sub inventory accesses cannot be empty")
    private List<@Valid UserSubInvAccessRequest> userSubInvAccesses = new ArrayList<>();
}


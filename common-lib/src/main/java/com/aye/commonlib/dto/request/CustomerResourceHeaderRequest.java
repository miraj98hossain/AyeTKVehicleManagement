package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;


@Data
public class CustomerResourceHeaderRequest {

    private Integer id;

    @NotNull(message = "Customer Account ID must not be null")
    private Integer custAccountId;

    @NotBlank(message = "Customer Name cannot be empty")
    @Size(max = 100, message = "Customer Name must be less than 100 characters")
    private String customerName;

    @NotBlank(message = "Account Number cannot be empty")
    @Size(max = 50, message = "Account Number must be less than 50 characters")
    private String accountNumber;

    @NotNull(message = "Organization ID must not be null")
    private Long orgId;

    @PastOrPresent(message = "Start date cannot be in the future")
    private Date start_date;

    @FutureOrPresent(message = "End date cannot be in the past")
    private Date end_date;

    @NotBlank(message = "Resource status cannot be empty")
    private String resourceStatus;

//    @NotNull(message = "Resource lines cannot be null")
//    @Size(min = 1, message = "At least one resource line must be provided")
//    @Valid
//    private List<CustomerResourceLineResponse> customerResourceLines;
}

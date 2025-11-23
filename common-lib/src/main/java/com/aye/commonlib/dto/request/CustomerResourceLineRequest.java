package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerResourceLineRequest {

    private Integer lineId;

    @NotNull(message = "Employee Resource ID must not be null")
    private Integer empResourceId;

    @NotNull(message = "Organization ID must not be null")
    private Integer orgId;

    @NotNull(message = "Customer header ID must not be null")
    private Integer custHeaderId;

    @PastOrPresent(message = "Start date cannot be in the future")
    private Date start_date;

    @FutureOrPresent(message = "End date cannot be in the past")
    private Date end_date;

    @NotBlank(message = "Resource status cannot be empty")
    private String resourceStatus;
}

package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class UserAccessTemltDtlRequest {

    private Integer id;

    @NotNull(message = "Organization hierarchy ID must not be null")
    private Long orgHierarchyId;
    private String orgHierarchyCode;
    @NotNull(message = "User access template ID must not be null")
    private Integer userAccessTempltId;

    @NotNull(message = "User menu ID must not be null")
    private Integer userMenuId;
    private String userMenuName;
    private String userMenuLevel;

    //    @NotNull(message = "Request group header ID must not be null")
    private Long requestGroupHeaderId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Start date cannot be in the future")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "End date cannot be in the past")
    private Date endDate;

    @NotBlank(message = "Detail name cannot be empty")
    @Size(max = 150, message = "Detail name must not exceed 150 characters")
    private String detailName;

    @NotNull(message = "User specific flag must not be null")
    private Boolean userSpecefic;

}


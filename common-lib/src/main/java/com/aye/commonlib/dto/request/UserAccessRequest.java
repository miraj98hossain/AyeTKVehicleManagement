package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserAccessRequest {
    private Integer id;

    @NotBlank(message = "Access number is required")
    @Size(max = 50, message = "Access number must be up to 50 characters")
    private String accessNumber;

    @NotNull(message = "User ID is required")
    private Integer userId;

    @NotNull(message = "User access template is required")
    private Integer userAccessTempltId;

    @NotBlank(message = "Start date is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Start date must be in format yyyy-MM-dd")
    private String start_date;

    @NotBlank(message = "End date is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "End date must be in format yyyy-MM-dd")
    private String end_date;
}

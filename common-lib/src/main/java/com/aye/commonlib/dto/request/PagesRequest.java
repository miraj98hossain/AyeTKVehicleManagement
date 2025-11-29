package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PagesRequest {

    private Long id;

    @NotBlank(message = "Page name cannot be empty")
    @Size(max = 150, message = "Page name must not exceed 150 characters")
    private String pageName;

    @NotBlank(message = "Page URL cannot be empty")
    private String pageUrl;

    @NotNull(message = "Status must not be null")
    private Boolean status;

    @NotNull(message = "Page group must not be null")
    private Integer pageGroup;

    @NotBlank(message = "Physical name cannot be empty")
    private String physicalName;

    @NotBlank(message = "Page type is required")
    private String pageType;

    @NotNull(message = "App Page ID must not be null")
    private Integer appPageId;

    @NotNull(message = "App ID must not be null")
    private Integer appId;

    @NotNull(message = "Short order must not be null")
    private Integer shortOrder;
    @Size(max = 255, message = "Function name must not exceed 255 characters")
    private String function;
}

package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSubMenuRequest {
    private Integer id;

    @NotBlank(message = "Menu name cannot be empty")
    @Size(max = 100, message = "Menu name must not exceed 100 characters")
    private String menuName;

    @NotBlank(message = "Page name cannot be empty")
    @Size(max = 150, message = "Page name must not exceed 150 characters")
    private String pageName;

    @NotNull(message = "User Menu ID must not be null")
    private Integer userMenuId;

    @NotNull(message = "Page ID must not be null")
    private Long pageId;

    @NotNull(message = "Active status must not be null")
    private Boolean isActive;
}

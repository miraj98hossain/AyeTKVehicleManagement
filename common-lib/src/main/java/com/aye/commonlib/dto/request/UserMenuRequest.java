package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserMenuRequest {
    private Integer id;
    @NotBlank(message = "Menu name cannot be empty")
    @Size(max = 100, message = "Menu name must not exceed 100 characters")
    private String menuName;
    @NotNull(message = "Module ID must not be null")
    private Long moduleId;
    @NotBlank(message = "Level cannot be empty")
    private String level;
    @NotNull(message = "Active status must not be null")
    private Boolean isActive;
    @NotBlank(message = "Page type cannot be empty")
    private String pageType;

}


package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AppModuleRequest {
    private Long id;
    @NotBlank(message = "Module name cannot be empty")
    @Size(max = 100, message = "Module name must not exceed 100 characters")
    private String moduleName;

    @NotBlank(message = "Module code cannot be empty")
    @Size(max = 50, message = "Module code must not exceed 50 characters")
    private String moduleCode;
}

package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestGroupLineRequest {
    private Long id;
    @NotNull(message = "Request Group Header ID must not be null")
    private Long requestGroupHeaderId;

    @NotBlank(message = "Request type cannot be empty")
    @Size(max = 50, message = "Request type must not exceed 50 characters")
    private String requestType;

    @NotNull(message = "Executable ID must not be null")
    private Long executableId;

    @NotNull(message = "Active status must not be null")
    private Boolean isActive;
}

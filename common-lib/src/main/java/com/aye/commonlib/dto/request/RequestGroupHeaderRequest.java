package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestGroupHeaderRequest {
    private Long id;

    @NotBlank(message = "Group name cannot be empty")
    @Size(max = 100, message = "Group name must not exceed 100 characters")
    private String groupName;

}

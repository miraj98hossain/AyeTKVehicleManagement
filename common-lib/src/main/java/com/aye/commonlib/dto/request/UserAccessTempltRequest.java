package com.aye.commonlib.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAccessTempltRequest {

    private Integer id;

    @NotBlank(message = "Template number is required")
    @Size(max = 50, message = "Template number must be up to 50 characters")
    private String tempNumber;

    @NotBlank(message = "Template description is required")
    @Size(max = 255, message = "Template description must be up to 255 characters")
    private String tempDesc;

    @NotEmpty(message = "Template details cannot be empty")
    private List<@Valid UserAccessTemltDtlRequest> userAccessTemltDtls;
}

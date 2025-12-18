package com.aye.commonlib.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepSetupRequest {
    private Long stepSetupId;
    @NotBlank(message = "Description  is required")
    private String description;
    @NotNull(message = "Org id is required")
    private Long orgId;
    @NotNull(message = "Org code is required")
    private String orgCode;
    @NotNull(message = "Inv Org id is required")
    private Long invOrg;
    @NotNull(message = "Inv org code is required")
    private String invOrgCode;
    @NotNull(message = "Active Status is required")
    private Integer isActive;

}

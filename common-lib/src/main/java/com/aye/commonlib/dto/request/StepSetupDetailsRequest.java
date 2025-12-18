package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepSetupDetailsRequest {

    private Long stepSetupDetailsId;
    @NotNull(message = "Factory id is required")
    private Long stepSetupId;
    @NotNull(message = "Step id is required")
    private Long stepId;
    private String stepName;
    @NotNull(message = "Step Details is active is required")
    private Integer isActive;
}

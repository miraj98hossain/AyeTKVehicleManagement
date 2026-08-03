package com.aye.dtoLib.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepSetupResponse {

    private Long stepSetupId;
    private Long orgId;
    private String orgCode;
    private Long invOrg;
    private String invOrgCode;
    private Boolean isActive;
    private String description;
    private Long createdBy;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;

}

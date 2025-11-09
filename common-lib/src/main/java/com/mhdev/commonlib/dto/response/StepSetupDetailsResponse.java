package com.mhdev.commonlib.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StepSetupDetailsResponse {
    private Long stepSetupDetailsId;
    private Long stepSetupId;
    private Long stepId;
    private String stepName;
    private Integer serialNo;
    private Integer isActive;
    private Long createdBy;
    private Date createdAt;
    private Long updatedBy;
    private Date updatedAt;
}

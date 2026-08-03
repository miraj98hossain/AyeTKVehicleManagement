package com.aye.dtoLib.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StepSetupDetailsResponse {
    private Long stepSetupDetailsId;
    private Long stepSetupId;
    private String setupDescription;
    private Long stepId;
    private String stepName;
    private String stepNameWithSetupDesc;
    private Integer serialNo;
    private Boolean isActive;
    private Long createdBy;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;
}

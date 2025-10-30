package com.mhdev.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepTransLinesResponse {
    private Long stepTransLinesId;
    private Long stepTransId;
    private Long stepId;
    private String stepName;
    private String stepStatus;
    private String remarks;
    private Long parentLineId;
    private Integer stage;
    private Long createdBy;
    private Date createdAt;
    private Long updatedBy;
    private Date updatedAt;
}

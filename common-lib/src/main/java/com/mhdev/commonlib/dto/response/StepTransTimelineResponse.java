package com.mhdev.commonlib.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StepTransTimelineResponse {
    private Long stepTransTLId;
    private Long stepTransLinesId;
    private Long stepId;
    private String stepStatus;
    private LocalDateTime ignitionTime;
}

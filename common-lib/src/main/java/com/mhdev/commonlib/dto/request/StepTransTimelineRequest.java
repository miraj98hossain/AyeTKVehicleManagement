package com.mhdev.commonlib.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepTransTimelineRequest {

    //    private Long stepTransTLId;
//    @NotNull(message = "Step Trans id is required")
//    private Long stepTransId;
    @NotNull(message = "Step Trans Lines id is required")
    private Long stepTransLinesId;
    @NotNull(message = "Step id is required")
    private Long stepId;
    @NotNull(message = "Step Status is required")
    private String stepStatus;
}

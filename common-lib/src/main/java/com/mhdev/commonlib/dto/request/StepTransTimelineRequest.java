package com.mhdev.commonlib.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepTransTimelineRequest {
    private Long stepTransTLId;
    @NotNull(message = "Step Trans Lines id is required")
    private Long stepTransLinesId;
    @NotNull(message = "Step id is required")
    private Long stepId;
    private LocalDateTime ignTimeN;
    private LocalDateTime ignTimeP;
    private LocalDateTime ignTimeW;
    private LocalDateTime ignTimeC;
    private LocalDateTime ignTimeR;
}

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
public class StepTransDetailsRequest {
    private Long stepTransDtlId;
    @NotNull(message = "Step Trans Id is Required")
    private Long stepTransId;
    private Long custAccountId;
    private String custName;
    private Long orderNumber;
    private String scheduleNo;
}

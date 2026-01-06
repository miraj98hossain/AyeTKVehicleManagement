package com.aye.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepTransDetailsResponse {
    private Long stepTransDtlId;
    private String stepTransDtlNo;
    private Long stepTransId;
    private String stepTransNo;
    private Long custAccountId;
    private String custName;
    private String scheduleNo;
    private Long orderNumber;
}

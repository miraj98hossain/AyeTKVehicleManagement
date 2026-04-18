package com.aye.dtoLib.dto.request;

import com.aye.enums.StepStatus;
import com.aye.enums.StepTransStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepTransFilter {
    private String searchKeyword;
    private StepTransStatus stepTransStatus = StepTransStatus.C;
    private StepStatus stepStatus = StepStatus.C;
    private Date fromDate;
    private Date toDate;
}
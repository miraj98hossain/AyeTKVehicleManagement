package com.aye.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StepTransResponse {
    private String stepTransNo;
    private Long stepSetupId;
    private String vehicleNumber;
    private String transportName;
    private String driverPhoneNo;
    private String driverName;
//    private String partyName;
//    private String item;
//    private Double quantity;

    private List<StepTransLinesResponse> stepTransLinesResponseList = new ArrayList<>();
    private Long createdBy;
    private Date createdAt;
    private Long updatedBy;
    private Date updatedAt;
}

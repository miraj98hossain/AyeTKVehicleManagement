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

public class StepTransResponse {
    private Long stepTransId;
    private String vehicleNumber;
    private Long orgId;
    private String driverPhoneNo;
    private String partyName;
    private String item;
    private Double quantity;
    private String transportName;
    private Long createdBy;
    private Date createdAt;
    private Long updatedBy;
    private Date updatedAt;
}

package com.aye.commonlib.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerResourceLineResponse {
    private Integer lineId;
    private Integer empResourceId;
    private Integer orgId;
    private Integer custHeaderId;
    private Integer custAccountId;
    private String customerName;
    private String accountNumber;
    private Date start_date;
    private String end_date;
    private String resourceStatus;
    private Integer createdBy;
    private Integer lastUpdateBy;
    private Date creationDate;
    private Date lastUpdateDate;
    private Date lastUpdateLogin;
}

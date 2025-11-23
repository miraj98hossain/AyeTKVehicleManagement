package com.aye.commonlib.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerResourceHeaderResponse {
    private Integer id;
    private Integer custAccountId;
    private String customerName;
    private String accountNumber;
    private Long orgId;
    private Date start_date;
    private Date end_date;
    private String resourceStatus;
    private Integer createdBy;
    private Integer lastUpdateBy;
    private String creationDate;
    private String lastUpdateDate;
    private String lastUpdateLogin;
    private List<CustomerResourceLineResponse> customerResourceLines;
}

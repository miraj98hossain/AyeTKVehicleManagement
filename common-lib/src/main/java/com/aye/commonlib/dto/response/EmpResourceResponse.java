package com.aye.commonlib.dto.response;

import lombok.Data;

@Data
public class EmpResourceResponse {
    private Integer id;
    private Integer orgId;
    private String resourceName;
    private String phoneNo;
    private String empNumber;
    private Integer empId;
    private String resourceNumber;
    private String start_date;
    private String end_date;
    private Boolean isMultipleUse;
    private Integer createdBy;
    private Integer lastUpdateBy;
    private String creationDate;
    private String lastUpdateDate;
    private String lastUpdateLogin;
}

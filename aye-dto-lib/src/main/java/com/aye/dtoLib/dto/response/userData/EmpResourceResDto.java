package com.aye.dtoLib.dto.response.userData;

import lombok.Data;

@Data
public class EmpResourceResDto {
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

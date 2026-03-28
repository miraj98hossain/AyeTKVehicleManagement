package com.aye.dtoLib.dto.response.userOrg;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MUserAccessResDto {
    private Integer accessId;

    private Long empId;

    private Integer custAcctId;

    private Integer tempNumber;

    private Integer dataAccessNumber;

    private String tempDesc;

    private String dataAccessDesc;

    private Date start_date = new Date();
    private Date end_date;

    private Integer createdBy;
    private Integer lastUpdateBy;
    private Date creationDate = new Date();
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;


}
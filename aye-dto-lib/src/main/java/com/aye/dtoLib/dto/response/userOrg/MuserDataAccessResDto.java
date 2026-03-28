package com.aye.dtoLib.dto.response.userOrg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuserDataAccessResDto {

    private Integer id;

    private Integer dataAccessNumber;
    private String accessLevel;
    private String accessValue;

    private String dataAccessDesc;

    private Integer orgAccess;

    private Date start_date = new Date();
    private Date end_date;

    private Integer createdBy;
    private Integer lastUpdateBy;
    private Date creationDate = new Date();
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;

}

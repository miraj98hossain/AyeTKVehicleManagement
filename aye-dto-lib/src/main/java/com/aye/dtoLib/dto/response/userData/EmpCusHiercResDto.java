package com.aye.dtoLib.dto.response.userData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpCusHiercResDto {


    private Integer uniqId;

    private String hierarchyType;

    private String value;

    private Integer valueId;
    private Integer parent;
    private Integer OrgAccess;
    private Date start_date = new Date();

    private String hierarchyValue;

    private String parentValue;

    private String orgName;

    private Date end_date;
    private Integer CreatedBy;
    private Integer LastUpdateBy;
    private Date creationDate = new Date();
    private Date LastUpdateDate;
    private Integer LastUpdateLogin;

    private Integer hierarchyCategory;

}

package com.aye.dtoLib.dto.response.userOrg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MUserAccessTemplResDto {


    private Integer tempId;
    private Integer tempNumber;
    private String tempDesc;
    private String menuId;
    private String parentId;
    private Integer createdBy;
    private Integer lastUpdateBy;
    private Date creationDate = new Date();
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;

    private Date start_date;
    private Date end_date;


    private MUserAccessTemplHdrResDto muserAccesstemplHdr;


}
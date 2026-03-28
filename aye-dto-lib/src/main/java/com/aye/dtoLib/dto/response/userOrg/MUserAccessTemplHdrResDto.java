package com.aye.dtoLib.dto.response.userOrg;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Toufiq on 8/25/2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MUserAccessTemplHdrResDto {
    private Integer id;
    private Integer tempNumber;
    private String tempDesc;
    private Integer createdBy;
    private Integer lastUpdateBy;
    private Date creationDate = new Date();
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;

    private Date start_date;
    private Date end_date;

    private List<MUserAccessTemplResDto> muserAccesstempls = new ArrayList<>();

}

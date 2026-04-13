package com.aye.dtoLib.dto.response.schedule;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleCustDto {


    private List<ScheduleLineDto> scheduleLines = new ArrayList<>();
    private Integer id;
    private ScheduleHeaderDto scheduleHeader;
    private Integer createdBy;
    private Date creationDate;
    private Integer lastUpdateBy;
    private Date lastUpdateLogin;
    private Date lastUpdateDate;
    private Integer custAcctId;
    private String customerName;
    private Integer custSiteId;
    private Integer shipToId;

}

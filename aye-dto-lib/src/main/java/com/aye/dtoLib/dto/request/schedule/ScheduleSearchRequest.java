package com.aye.dtoLib.dto.request.schedule;


import com.aye.enums.RegularData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Munna on 11/20/2023.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleSearchRequest {

    RegularData.ScheduleStstus status;
    private Long orgId;
    private Long invOrgId;
    private Integer custAcctId;
    private String customerName;
    private Integer custSiteId;
    private Integer shipToId;
    private Integer userId;
    private Integer scdHeaderId;
    private String scheduleNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date fromrDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date toDate;
    private String itemClass;
    private String subInventory;

}

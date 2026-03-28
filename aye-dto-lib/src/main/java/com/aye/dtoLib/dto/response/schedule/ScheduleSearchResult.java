package com.aye.dtoLib.dto.response.schedule;


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
public class ScheduleSearchResult {

    private Long orgId;
    private Long invOrgId;
    private Integer custAcctId;
    private String customerName;

    private String custSiteId;

    private Integer scdHeaderId;

    private String scheduleNumber;


    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date fromrDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date toDate;
    private RegularData.ScheduleStstus status;

    public ScheduleSearchResult(Long orgId,
                                Long invOrgId,
                                Integer custAcctId,
                                String customerName,
                                Integer scdHeaderId,
                                String scheduleNumber,
                                Date fromrDate,
                                Date toDate,
                                RegularData.ScheduleStstus status) {

        this.orgId = orgId;
        this.invOrgId = invOrgId;
        this.custAcctId = custAcctId;
        this.customerName = customerName;
        this.scdHeaderId = scdHeaderId;
        this.scheduleNumber = scheduleNumber;
        this.fromrDate = fromrDate;
        this.toDate = toDate;
        this.status = status;
    }

}

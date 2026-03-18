package com.aye.commonlib.dto.response.report;


import com.aye.commonlib.dto.response.MUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Clob;
import java.util.Date;

/**
 * Created by Toufiq on 10/13/2019.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDataHtmlResDto {
    private Long id;

    private String rptName;


    private MUserResponse muser;

    private String autoNumber;

    private Clob htmlData;

    private String htmlStringData;

    private Boolean status;

    private Date startDate;

    private Date endDate;
}

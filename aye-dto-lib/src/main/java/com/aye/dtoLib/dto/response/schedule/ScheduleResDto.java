package com.aye.dtoLib.dto.response.schedule;


import com.aye.enums.ScheduleJobGroup;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Toufiq on 5/23/2019.
 */

@Setter
@Getter

public class ScheduleResDto {

    private Long id;
    private String name;

    private ScheduleJobGroup jobGroup;

    private String jobClass;

    private String cronExpression;

    private Integer delayRate;

    private Boolean cronJob;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;


}

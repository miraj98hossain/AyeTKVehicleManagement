package com.aye.commonlib.dto.request.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleHeaderReqDto {

    private List<ScheduleCustReqDto> scheduleCusts = new ArrayList();
    private Integer id;
    private Integer createdBy;
    private Date creationDate;
    private Integer lastUpdateBy;
    private Date lastUpdateLogin;
    private Date lastUpdateDate;
    private Long orgId;
    private String scheduleNumber;
    private String status;
    private Long invOrgId;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    private Date startDate;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    private Date endtDate;
    private String userName;

}

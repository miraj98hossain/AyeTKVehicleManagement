package com.aye.dtoLib.dto.response.schedule;

import com.aye.enums.RegularData;
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
public class ScheduleHeaderResDto {

    private List<ScheduleCustResDto> scheduleCusts = new ArrayList();
    private Integer id;
    private Integer createdBy;
    private Date creationDate;
    private Integer lastUpdateBy;
    private Date lastUpdateLogin;
    private Date lastUpdateDate;
    private Long orgId;
    private String scheduleNumber;
    private RegularData.ScheduleStstus status;
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

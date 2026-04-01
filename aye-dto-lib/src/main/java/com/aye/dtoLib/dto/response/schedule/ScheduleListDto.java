package com.aye.dtoLib.dto.response.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleListDto {
    Long orgId;

    Long invOrgId;

    Integer createdBy;

    Integer id;

    String scheduleNumber;
}
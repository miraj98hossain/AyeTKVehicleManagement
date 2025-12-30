package com.aye.commonlib.dto.response;

import lombok.Data;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 14:08
 * @project: AyeTKVehicleManagement
 */
@Data
public class BeforeTripWDsVResponse {
    private Long scheduleId;
    private String scheduleNumber;
    private String partyName;
}

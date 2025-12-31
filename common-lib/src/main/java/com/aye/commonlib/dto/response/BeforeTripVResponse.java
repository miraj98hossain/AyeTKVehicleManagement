package com.aye.commonlib.dto.response;

import lombok.Data;

import java.util.Date;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 12:06
 * @project: AyeTKVehicleManagement
 */
@Data
public class BeforeTripVResponse {
    private Long orderNumber;
    private Date orderedDate;
    private String partyName;
}

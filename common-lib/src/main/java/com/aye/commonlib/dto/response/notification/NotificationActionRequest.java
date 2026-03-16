package com.aye.commonlib.dto.response.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Miraj
 * @date: 21/01/2026
 * @time: 16:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationActionRequest {
    private String ActionType;
    private String reason;
    private String ActionValue;
    private Integer orgId;
}

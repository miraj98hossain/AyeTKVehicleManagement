package com.aye.dtoLib.dto.response.notification;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MWfNotifInfoResDto {

    private BigDecimal notificationId;

    private Long userId;

    private String type;

    private String recipientRole;

    private String fromUser;

    private String subject;

    private LocalDateTime sentDate;

    private String itemKey;

    private Character actionType;
}

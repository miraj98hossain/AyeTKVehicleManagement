package com.aye.commonlib.dto.response.notification;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDashBoard {


    private BigDecimal userId;

    private String itemType;

    private String receipientRole;

    private String fromUser;

    private String subject;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sentDate;


    private String itemKey;

    private BigDecimal notificationId;

    private char actionType;


}

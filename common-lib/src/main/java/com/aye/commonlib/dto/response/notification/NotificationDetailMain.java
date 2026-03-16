package com.aye.commonlib.dto.response.notification;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDetailMain {

    private BigDecimal orgId;

    private Date trxDate;

    private String number;

    private String trnsType;

    private BigDecimal totalAmount;

    private String itemType;

    private String itemkey;

    private String recipientRole;

    private String fromUser;

    private String toUser;

    private String subJect;

    private Date sentDate;

    private String custSupName;

    private String address;

    private BigDecimal notificationId;

    private List<NotificationDetailDet> detail;

    private List<NotificationAction> actions;

}

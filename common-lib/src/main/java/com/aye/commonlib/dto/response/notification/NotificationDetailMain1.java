package com.aye.commonlib.dto.response.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDetailMain1 {

    private BigDecimal orgId;

    private String number;

    private String trnsType;

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

    private Date trxDate;

    private Boolean viewAttachment;

    private String htmlTag;

    private List<NotificationAction> actions;

}

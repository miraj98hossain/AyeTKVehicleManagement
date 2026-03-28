package com.aye.dtoLib.dto.response.notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Setter
@Getter

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

    public NotificationDetailMain1(BigDecimal orgId, String number, String trnsType, String itemType, String itemkey,
                                   String recipientRole, String fromUser, String toUser, String subJect, Date sentDate, String custSupName,
                                   String address, BigDecimal notificationId, Date trxDate, String htmlTag, List<NotificationAction> actions) {

        this.orgId = orgId;
        this.number = number;
        this.trnsType = trnsType;
        this.itemType = itemType;
        this.itemkey = itemkey;
        this.recipientRole = recipientRole;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.subJect = subJect;
        this.sentDate = sentDate;
        this.custSupName = custSupName;
        this.address = address;
        this.notificationId = notificationId;
        this.trxDate = trxDate;
        this.htmlTag = htmlTag;
        this.actions = actions;
    }

}

package com.aye.commonlib.dto.response.notification;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MNotificationDtlVResDtO {


    private Long notificationId;

    private Long orgId;

    private String trxNum;

    private String trxType;

    private String itemType;

    private String itemKey;

    private String recipientRole;

    private String fromUser;

    private String toUser;

    private String subject;

    private LocalDateTime sentDate;

    private String vendorName;

    private String vendorAddress;

    private Long headerId;

    private LocalDateTime trxDate;
    private String dtlFunction;
}

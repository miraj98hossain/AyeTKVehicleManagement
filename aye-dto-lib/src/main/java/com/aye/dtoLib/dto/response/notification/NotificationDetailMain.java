package com.aye.dtoLib.dto.response.notification;


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

    public NotificationDetailMain(BigDecimal OrgId,
                                  String PoNumber,
                                  String PoType,
                                  BigDecimal totalAmount,
                                  String ItemType,
                                  String Itemkey,
                                  String RecipientRole,
                                  String FromUaser,
                                  String ToUser,
                                  String SubJect,
                                  Date SentDate,
                                  String VendorName,
                                  String VendorAddress,
                                  BigDecimal NotificationId,
                                  List<NotificationDetailDet> Detail,
                                  List<NotificationAction> actions,
                                  Date TrxDate) {
        this.orgId = OrgId;
        this.number = PoNumber;
        this.trnsType = PoType;
        this.totalAmount = totalAmount;
        this.itemType = ItemType;
        this.itemkey = Itemkey;
        this.recipientRole = RecipientRole;
        this.fromUser = FromUaser;
        this.toUser = ToUser;
        this.subJect = SubJect;
        this.sentDate = SentDate;
        this.custSupName = VendorName;
        this.address = VendorAddress;
        this.notificationId = NotificationId;
        this.detail = Detail;
        this.actions = actions;
        this.trxDate = TrxDate;
    }

}

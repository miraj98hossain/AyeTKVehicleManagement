package com.aye.dtoLib.dto.response.notification;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NotificationAction {

    private String lookupTypes;

    private String displayName;

    private String itemType;

    private String lookupCode;

    private String wfType;


    private List<NotificationActReasonResDto> notificationActReasons = new ArrayList<>();

    public NotificationAction() {
    }

    public NotificationAction(String LookupTypes, String DisplayName, String ItemType, String LookupCode,
                              String WfType) {
        this.lookupTypes = LookupTypes;
        this.displayName = DisplayName;
        this.itemType = ItemType;
        this.lookupCode = LookupCode;
        this.wfType = WfType;
    }

    public NotificationAction(String LookupTypes, String DisplayName, String ItemType, String LookupCode,
                              String WfType, List<NotificationActReasonResDto> notificationActReasons) {
        this.lookupTypes = LookupTypes;
        this.displayName = DisplayName;
        this.itemType = ItemType;
        this.lookupCode = LookupCode;
        this.wfType = WfType;
        this.notificationActReasons = notificationActReasons;
    }


}

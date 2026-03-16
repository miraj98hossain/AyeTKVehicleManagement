package com.aye.commonlib.dto.response.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationActReasonResDto {

    private Integer id;
    private String lookupCode;
    private String wfType;
    private String reason;

}
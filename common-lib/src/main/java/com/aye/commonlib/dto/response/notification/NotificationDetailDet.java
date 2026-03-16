package com.aye.commonlib.dto.response.notification;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDetailDet {


    private BigDecimal itemId;

    private String itemDescription;

    private String uomLookUpCode;

    private BigDecimal unitPrice;

    private BigDecimal quantity;


}

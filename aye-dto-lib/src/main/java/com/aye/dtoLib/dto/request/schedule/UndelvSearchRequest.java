package com.aye.dtoLib.dto.request.schedule;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Munna on 11/20/2023.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UndelvSearchRequest {

    private Long orgId;
    private Long invOrgId;
    private Integer custAcctId;
    private String customerName;


    private Integer custSiteId;

    private Integer shipToId;

    private String itemCategories;

    private String itemFamily;

    private String itemClass;

    private Integer ordHeaderId;

    private Long orderNumber;

    private String orderItem;

    private String modeOfTransport;

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date fromrDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date toDate;


    private String subInventory;


}

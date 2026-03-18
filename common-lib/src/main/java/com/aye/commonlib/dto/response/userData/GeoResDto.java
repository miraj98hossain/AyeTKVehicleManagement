package com.aye.commonlib.dto.response.userData;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Toufiq on 9/24/2019.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoResDto {


    private Integer id;

    private GeoRefTrnsSource trnsSource;

    private GeoRefAction refAction;


    private Integer trnsId;


    private String latitude;


    private String longitude;


    private Integer createdBy;


    private Date creationDate;


    private Integer lastUpdateBy;

    private Date lastUpdateLogin;


    private Date lastUpdateDate;


    public enum GeoRefTrnsSource {
        DASHBOARD, ORDER, COLLECTION, BULKOEDER, BULKPAYMENT, CUSTSTOCK
    }

    public enum GeoRefAction {
        GET, SAVE, UPDATE, DELETE, POST
    }
}

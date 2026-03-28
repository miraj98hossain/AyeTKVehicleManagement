package com.aye.dtoLib.dto.response.geo;


import com.aye.dtoLib.dto.response.MUserResponse;
import com.aye.enums.GeoRefTrnsSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by toufiq on 5/11/2021.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoSerach {

    private Date fromDate;
    private Date toDate;
    private GeoRefTrnsSource trnsSource;
    private String trnsNumber;
    private Integer trnsId;
    private MUserResponse createdBy;


}

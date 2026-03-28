package com.aye.dtoLib.dto.request.geo;


import com.aye.enums.GeoRefAction;
import com.aye.enums.GeoRefTrnsSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Toufiq on 9/24/2019.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoReqDto {

    private Integer id;

    private GeoRefTrnsSource trnsSource;

    private GeoRefAction refAction;

    private Integer trnsId;

    private String latitude;

    private String longitude;

}

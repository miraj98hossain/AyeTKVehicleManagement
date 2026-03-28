package com.aye.dtoLib.dto.response.userData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuserDataCustDResDto {


    private Integer id;


    private Integer custAcctSiteId;


    private MUserDataCustMResDto muserDataCustD;


    private String autonumber;


    private Integer partyId;


    private Integer billToId;


    private Integer shipToId;


    private String shipToLocation;


}

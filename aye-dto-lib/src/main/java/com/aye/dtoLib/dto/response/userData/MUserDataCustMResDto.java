package com.aye.dtoLib.dto.response.userData;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MUserDataCustMResDto {


    private Integer id;


    private Long orgId;


    private String autoNumber;


    private Integer partyId;


    private String accountNumber;


    private Integer custAcctId;


    private String customerName;


    private Long setId;


    private String address;


    private List<MuserDataCustDResDto> custDs = new ArrayList<MuserDataCustDResDto>();

}

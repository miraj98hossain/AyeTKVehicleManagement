package com.aye.commonlib.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutMenuOrg {


    private Integer orgId;


    private String orgCode;


    private List<OutMenuTrns> access;

}

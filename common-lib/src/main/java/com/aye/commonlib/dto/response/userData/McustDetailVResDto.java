package com.aye.commonlib.dto.response.userData;


import lombok.Getter;
import lombok.Setter;

/**
 * Created by toufiq on 8/23/2021.
 */
@Setter
@Getter
public class McustDetailVResDto {

    private Long id;


    private Long orgId;


    private Integer partyId;


    private String accountNumber;


    private Integer custAcctId;


    private String partyName;


    private String partyNumber;


    private Integer custAcctSiteId;


    private String address;

}

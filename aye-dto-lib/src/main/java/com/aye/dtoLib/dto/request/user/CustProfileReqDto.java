package com.aye.dtoLib.dto.request.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Toufiq on 8/19/2019.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustProfileReqDto {

    private Integer id;

    private Integer orgId;

    private String accountNumber;

    private Integer custAcctId;

    private String name;

    private String address;

    private Integer userId;

    private String userName;

}

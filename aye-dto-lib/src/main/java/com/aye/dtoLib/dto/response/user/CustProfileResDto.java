package com.aye.dtoLib.dto.response.user;


import com.aye.dtoLib.dto.response.MUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Toufiq on 8/19/2019.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustProfileResDto {

    private Integer id;

    private Integer orgId;

    private String accountNumber;

    private Integer custAcctId;

    private String name;

    private String address;

    private MUserResponse userId;

    private String userName;

    private Integer createdBy;

    private Integer lastUpdateBy;

    private Date creationDate = new Date();

    private Date lastUpdateDate;

    private Integer lastUpdateLogin;
}

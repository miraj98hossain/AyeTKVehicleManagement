package com.aye.dtoLib.dto.response.userOrg;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MSubInvAccessResDto {
    private final Date creationDate = new Date();
    private Integer id;
    private Integer accessNumber;
    private Integer orgId;
    private Integer organizationId;
    private String subInv;
    private Integer createdBy;
    private Integer lastUpdateBy;
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;

}

package com.aye.dtoLib.dto.response.userOrg;

import com.aye.enums.AccessCodeLevel;
import com.aye.enums.AccessCodeType;
import lombok.Data;

@Data
public class UserCodeAccessResponse {
    private Long id;
    private Integer userId;
    private String userName;
    private AccessCodeType accessCodeType;
    private AccessCodeLevel accessCodeLevel;
    private Long orgHierarchyId;
    private String orgHierarchyCode;
    private Long invInfoId;
    private String invInfoCode;
    private String invInfoName;
    private Long itemCatCombId;
    private String itemCatComb;
    private Long scaleSetupId;
    private String scaleSetupName;
    private String scaleSetupIp;
}

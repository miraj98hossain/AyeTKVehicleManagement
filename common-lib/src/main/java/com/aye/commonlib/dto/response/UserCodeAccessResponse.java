package com.aye.commonlib.dto.response;

import lombok.Data;

@Data
public class UserCodeAccessResponse {
    private Long id;
    private Integer userId;
    private String userName;
    private String accessCodeType;
    private String accessCodeLevel;
    private Long orgHierarchyId;
    private String orgHierarchyCode;
    private Long invInfoId;
    private String invInfoCode;
    private String invInfoName;
}

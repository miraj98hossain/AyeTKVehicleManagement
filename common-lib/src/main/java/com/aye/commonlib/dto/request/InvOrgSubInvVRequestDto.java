package com.aye.commonlib.dto.request;

import lombok.Data;

@Data
public class InvOrgSubInvVRequestDto {

    private String subInvName;

    private Long invOrgsId;

    private String invOrgsName;

    private Long orgHierarchyId;

    private String orgName;

    // getters & setters
}

package com.aye.commonlib.dto.response.userOrg;

import lombok.Data;

@Data
public class InvOrgSubInvVResponseDto {

    private Long id;

    private String subInvName;

    private Long invOrgsId;

    private String invOrgsName;

    private Long orgHierarchyId;

    private String orgName;

}

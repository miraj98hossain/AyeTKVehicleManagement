package com.aye.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccessInvOrgResponse {
    private Long id;
    private Integer invOrgId;
    private String invOrgName;
    private Integer userAccessTemltDtlId;
    private Long orgId;
    private String tempDtlDetailName;
    private List<UserTransactionTypesResponse> userTransactionTypes;
}

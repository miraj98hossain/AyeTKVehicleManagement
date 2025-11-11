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
    private Integer id;
    private Integer invOrgId;
    private Integer userAccessTemltDtlId;
    private List<UserTransactionTypesResponse> userTransactionTypes;
}

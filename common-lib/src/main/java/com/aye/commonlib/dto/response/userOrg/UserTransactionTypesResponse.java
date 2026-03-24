package com.aye.commonlib.dto.response.userOrg;

import com.aye.enums.TrnsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTransactionTypesResponse {
    private Long id;
    private TrnsType trnsType;
    private Long trnsTypeId;
    private String name;
    private String description;
    private Long userAccessInvOrgId;
    private Long invOrgId;
    private String startDate;
    private String endDate;
    private Boolean isMandatory;
    private List<UserSubInvAccessResponse> userSubInvAccesses = new ArrayList<>();
}

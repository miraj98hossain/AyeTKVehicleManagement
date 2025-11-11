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
public class UserAccessTemltDtlResponse {
    private Integer id;
    //org details
    private Integer orgHierarchyId;
    private String orgHierarchyName;
    private String orgHierarchyCode;
    //temp details
    private Integer UserAccessTemltId;
    private String UserAccessTemltNumber;
    private String UserAccessTemltDesc;
    private UserMenuResponse userMenu;
    private String menuHeaderName;
    private String menuLevel;
    private String orgHerName;
    private String reqGrpHdrName;
    private String startDate;
    private String endDate;
    private String detailName;
    private Boolean userSpecefic;
    private List<UserAccessInvOrgResponse> userAccessInvOrgs;
}
package com.aye.dtoLib.dto.response.userOrg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccessTemltDtlResponse {
    private Integer id;
    //org details
    private Long orgHierarchyId;
    private String orgHierarchyName;
    private String orgHierarchyCode;
    private String orgDimOrderEnabled;
    private String orgCanChangePrice;
    //temp details
    private Integer userAccessTemltId;
    private String userAccessTemltNumber;
    private String userAccessTemltDesc;
    private UserMenuResponse userMenu;
    private String menuHeaderName;
    private String menuLevel;
    private String orgHerName;
    private String reqGrpHdrName;
    private Date startDate;
    private Date endDate;
    private String detailName;
    private Boolean userSpecefic;
    private List<UserAccessInvOrgResponse> userAccessInvOrgs;
}
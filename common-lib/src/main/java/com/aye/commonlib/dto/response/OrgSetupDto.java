package com.aye.commonlib.dto.response;


import com.aye.commonlib.dto.response.userOrg.OrgHierarchyResponse;
import lombok.Data;

//import javax.persistence.SequenceGenerator;

@Data
public class OrgSetupDto {


    private Long orgId;


    private String orgCode;

    private String orgName;


    public OrgSetupDto(Long orgId, String orgCode, String orgName) {

        this.orgId = orgId;
        this.orgCode = orgCode;
        this.orgName = orgName;
    }

//    public OrgSetupDto(OrgSetup o) {
//
//        this.orgId = o.getOrgId();
//        this.orgCode = o.getOrgCode();
//        this.orgName = o.getOrgName();
//    }

    public OrgSetupDto(OrgHierarchyResponse o) {

        this.orgId = o.getOrgId();
        this.orgCode = o.getOrgCode();
        this.orgName = o.getOrgName();
    }
}

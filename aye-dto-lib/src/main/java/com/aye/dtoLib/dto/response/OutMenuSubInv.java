package com.aye.dtoLib.dto.response;


import com.aye.dtoLib.dto.response.userOrg.InventoryInformationResponse;
import com.aye.dtoLib.dto.response.userOrg.OrgHierarchyResponse;
import com.aye.dtoLib.dto.response.userOrg.UserSubInvAccessResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutMenuSubInv {


    private Long orgId;

    private Long organizationId;

    private String subInv;

    public OutMenuSubInv() {
    }

    public OutMenuSubInv(Long p1, Long p2, String p3) {
        this.setOrgId(p1);
        this.setOrganizationId(p2);
        this.setSubInv(p3);

    }

    public OutMenuSubInv(UserSubInvAccessResponse usa, OrgHierarchyResponse orgSetup, InventoryInformationResponse uai
    ) {
        this.setOrgId(orgSetup.getId());
        this.setOrganizationId(uai.getId());
        this.setSubInv(usa.getSubInv());

    }


}

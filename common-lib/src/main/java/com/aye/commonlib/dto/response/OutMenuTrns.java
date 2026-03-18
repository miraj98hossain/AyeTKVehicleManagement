package com.aye.commonlib.dto.response;


import com.aye.commonlib.dto.response.userOrg.InventoryInformationResponse;
import com.aye.commonlib.dto.response.userOrg.OrgHierarchyResponse;
import com.aye.commonlib.dto.response.userOrg.UserTransactionTypesResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class OutMenuTrns {


    private Long orgId;


    private Long organizationId;


    private String organizationCode;

    private Long orderTrnsId;

    private String orderTrnsType;

    private String canChangePrice;

    private List<OutMenuSubInv> outMenuSubInv;


    public OutMenuTrns() {
    }

    public OutMenuTrns(Long orgId, Long organizationId,
                       String organizationCode
            , BigDecimal ordtrnsId, String orderTrnsType,
                       String canChagnePrice, List<OutMenuSubInv> outMenuSub) {
        this.setOrgId(orgId);

        this.setOrganizationId(organizationId);
        this.setOrganizationCode(organizationCode);
        this.setOrderTrnsId(ordtrnsId.longValue());
        this.setOrderTrnsType(orderTrnsType);
        this.setCanChangePrice(canChagnePrice);

        this.setOutMenuSubInv(outMenuSub);

    }

    public OutMenuTrns(UserTransactionTypesResponse userTransactionTypes, OrgHierarchyResponse orgSetup, InventoryInformationResponse uai
    ) {
        this.setOrgId(orgSetup.getOrgId());
        this.setOrganizationId(uai.getId());
        this.setOrganizationCode(uai.getName());
        this.setOrderTrnsId(userTransactionTypes.getTrnsTypeId());
        this.setOrderTrnsType(userTransactionTypes.getName());
        this.setCanChangePrice(orgSetup.getCanChangePrice());
    }

}

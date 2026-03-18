package com.aye.commonlib.dto.response;


import com.aye.commonlib.dto.response.userOrg.AppModuleResponse;
import com.aye.commonlib.dto.response.userOrg.OrgHierarchyResponse;
import com.aye.commonlib.dto.response.userOrg.PagesResponse;
import com.aye.commonlib.dto.response.userOrg.UserSubMenuResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OutMenuSub {

    private String menuDesc;


    private Integer menuId;


    private String menuModule;


    private String menuType;


    private String pageName;


    private Integer orgId;


    private String orgCode;


    private BigDecimal lovShow;


    private String dimOrderEnabled;


    private List<OutMenuTrns> access;


    public OutMenuSub() {
    }

    public OutMenuSub(String p1, BigDecimal p2, String p3, String p4, String p7, BigDecimal p5,
                      Integer p8, String orgCode, String dimEnable, List<OutMenuTrns> p6
    ) {
        this.setMenuDesc(p1);
        this.setMenuId(p2.intValueExact());
        this.setMenuModule(p3);
        this.setMenuType(p4);
        this.setPageName(p7);
        this.setLovShow(p5);
        this.setAccess(p6);
        this.setOrgId(p8.longValue());
        this.setOrgCode(orgCode);
        this.setDimOrderEnabled(dimEnable);

    }

    public OutMenuSub(UserSubMenuResponse subMenu, PagesResponse pages,
                      AppModuleResponse am, OrgHierarchyResponse orgHierarchy
    ) {

        this.setMenuDesc(pages.getPageName());
        this.setMenuId(subMenu.getId());
        this.setMenuModule(am.getModuleCode());
        this.setMenuType("SubMenu");
        this.setPageName(pages.getPhysicalName());
        this.setLovShow(new BigDecimal(1));
        this.setOrgId(orgHierarchy.getId());
        this.setOrgCode(orgHierarchy.getCode());
        this.setDimOrderEnabled(orgHierarchy.getDimOrderEnabled());


    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public void setMenuModule(String menuModule) {
        this.menuModule = menuModule;
    }


    public void setLovShow(BigDecimal lovShow) {
        this.lovShow = lovShow;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }


    public void setOrgId(Long orgId) {
        this.orgId = orgId.intValue();
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public void setAccess(List<OutMenuTrns> access) {
        this.access = access;
    }

    public void setDimOrderEnabled(String dimOrderEnabled) {
        this.dimOrderEnabled = dimOrderEnabled;
    }
}

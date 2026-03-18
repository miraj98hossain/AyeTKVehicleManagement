package com.aye.commonlib.dto.response;


import com.aye.commonlib.dto.response.userOrg.UserAccessTemltDtlResponse;
import com.aye.commonlib.dto.response.userOrg.UserMenuResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class OutMenuMain {

    private String menuDesc;

    private Integer menuId;

    private String menuModule;

    private String menuType;

    private List<OutMenuSub> outMenuSub;


    public OutMenuMain() {
    }

    public OutMenuMain(String p1, BigDecimal p2, String p3, String p4, List<OutMenuSub> p5) {
        this.setMenuDesc(p1);
        this.setMenuId(p2.intValueExact());
        this.setMenuModule(p3);
        this.setMenuType(p4);
        this.setOutMenuSub(p5);


    }

    public OutMenuMain(UserMenuResponse userMenu) {
        this.setMenuDesc(userMenu.getMenuName());
        this.setMenuId(userMenu.getId());
        this.setMenuModule(userMenu.getMenuName());
        this.setMenuType("Main");
    }

    public OutMenuMain(UserAccessTemltDtlResponse uatd, UserMenuResponse userMenu) {

        this.setMenuDesc(userMenu.getMenuName());
        this.setMenuId(userMenu.getId());

        this.setMenuModule(userMenu.getModuleName());
        this.setMenuType("Main");


    }


}

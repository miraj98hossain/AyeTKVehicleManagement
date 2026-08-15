package com.aye.backendservice.controller;


import com.aye.backendservice.service.orgManagement.InvInformationsService;
import com.aye.backendservice.service.orgManagement.OrgHierarchyService;
import com.aye.backendservice.service.orgManagement.UserMenuService;
import com.aye.backendservice.service.orgManagement.UserTrnsAndSubinvAccessService;
import com.aye.entity.InventoryInformations;
import com.aye.entity.OrgHierarchy;
import com.aye.entity.UserMenu;
import com.aye.entity.order.OrdTrnsTypesV;
import com.aye.enums.OrgType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by toufiq on 11/6/2021.
 */
@Controller
@RequestMapping("/ACRC")
public class AutoCompleteReqControllerOrgMng {

    @Autowired
    private OrgHierarchyService orgHierarchyService;

    @Autowired
    private InvInformationsService invInformationsService;

    @Autowired
    private UserMenuService userMenuService;

    @Autowired
    private UserTrnsAndSubinvAccessService userTrnsAndSubinvAccessService;


    @RequestMapping(value = "/getMenuList", method = RequestMethod.GET)
    public @ResponseBody
    List<UserMenu> getAllMenu() {

        List<UserMenu> allMenu = this.userMenuService.getAllMenu();
        return allMenu;
    }


    @RequestMapping(value = "/getOrgHierarchy/{type}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<OrgHierarchy> getOrgHierarchy(@PathVariable("type") OrgType type) {
        List<OrgHierarchy> orgHierarchy = this.orgHierarchyService.findByType(type);
        return orgHierarchy;
    }

    @RequestMapping(value = "/getOrgHierarchyInvOrgOrderTrnsType/{orgId}/{invOrgId}/{type}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<OrdTrnsTypesV> getOrgHierarchyIngOrderType(@PathVariable("orgId") Long orgId, @PathVariable("invOrgId") Long invOrgId,
                                                    @PathVariable("type") String type) {
        OrgHierarchy orgHierarchy = this.orgHierarchyService.findById(orgId);
        InventoryInformations inv = this.invInformationsService.findOne(invOrgId);
        OrdTrnsTypesV ordTrnsTypesV = new OrdTrnsTypesV();
        ordTrnsTypesV.setOrgHierarchy(orgHierarchy);
        ordTrnsTypesV.setInvOrgs(inv);
        ordTrnsTypesV.setTypeName(type);
        List<OrdTrnsTypesV> ordTrnsTypesVs = this.userTrnsAndSubinvAccessService.searchOrdTrnsTypesV(ordTrnsTypesV);
        return ordTrnsTypesVs;
    }
}

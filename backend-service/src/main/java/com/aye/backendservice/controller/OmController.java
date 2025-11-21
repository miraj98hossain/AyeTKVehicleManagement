package com.aye.backendservice.controller;

import com.aye.RestfulServer.model.UserAccessTemltDtl;
import com.aye.RestfulServer.model.common.AppModuleCode;
import com.aye.RestfulServer.model.om.AppModule;
import com.aye.RestfulServer.model.om.OrgHierarchy;
import com.aye.RestfulServer.service.AppModuleService;
import com.aye.RestfulServer.service.CommonColumnServiceImpl;
import com.aye.RestfulServer.service.OrgHierarchyService;
import com.aye.RestfulServer.service.UserAccessTempltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/om")
public class OmController {


    @Autowired
    private UserAccessTempltService userAccessTempltService;
    @Autowired
    private AppModuleService appModuleService;
    @Autowired
    private OrgHierarchyService orgHierarchyService;
    @Autowired
    private CommonColumnServiceImpl commonColumnService;


    private void setModel(List<UserAccessTemltDtl> userMenus,
                          @PathVariable Integer pageId,
                          @PathVariable("mode") String mode,
                          @PathVariable("temltId") Integer temltId, ModelMap model, String type) {
        model.addAttribute("manus", userMenus);
        model.addAttribute("pageId", pageId);
        model.addAttribute("mode", mode);
        model.addAttribute("temltId", temltId);

        if (type == "BG") {
            AppModule appModule = this.appModuleService.findByCode(AppModuleCode.GL);
//            List<KeySegmentHeader> keySegmentHeaders= this.keySegmentHeaderService.findByModule(appModule);
//            model.addAttribute("keySegmentHeader", keySegmentHeaders);
//            model.addAttribute("calendarHeaders", this.calendarService.findByAppModule(appModule));
            model.addAttribute("bgIndx", this.orgHierarchyService.findAllBg());
        } else {
            OrgHierarchy bg = this.userAccessTempltService.findByDtlId(temltId).getOrgHierarchy();
//            List<SegmentLine> segLineOrg = new ArrayList<>();
//            segLineOrg =setSegmentLineData(bg.getKeySegmentHeader());
//            model.addAttribute("segLineOrg", segLineOrg);
//            model.addAttribute("currency", this.currencyService.findall());
            model.addAttribute("bg", bg.getCode());
            model.addAttribute("orgHierarchyIndx", this.orgHierarchyService.findByHierarchy(this.userAccessTempltService.findByDtlId(temltId).getOrgHierarchy()));
        }


    }


}

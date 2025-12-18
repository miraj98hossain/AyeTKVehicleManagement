/**
 * @author: Miraj
 * @date: 17/12/2025
 * @time: 10:01
 * @project: AyeTKVehicleManagement
 */
package com.aye.backendservice.config;

import com.aye.RestfulServer.model.*;
import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OnStartApp implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PageService pageService;
    @Autowired
    private MuserService muserService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        CommonColumn cc = new CommonColumn();
        Muser museru = this.muserService.findById(15677);
        cc.setCreatedBy(museru);
        cc.setCreationDate(new Date());

        for (PageUrl u : PageUrl.values()) {
//            System.out.println(u.toString()+" "+u.getGroup());
            if (this.pageService.findByName(u.toString()) == null) {
//                System.out.println(1);

                Pages p = new Pages();
                p.setStatus(true);
                p.setPageName(u.name());
                p.setPageUrl(u);
                p.setPageGroup(u.getGroup());
                p.setPageType(RoleTypes.WEB);
                p.setColumn(cc);
                pageService.save(p);


            } else {
//                System.out.println(2);
                Pages p = this.pageService.findByName(u.toString());
                p.setPageName(u.name());
                p.setPageUrl(u);
                p.setPageGroup(u.getGroup());
                p.setPageType(RoleTypes.WEB);
                p.setColumn(cc);
                pageService.save(p);

            }
        }
    }
}


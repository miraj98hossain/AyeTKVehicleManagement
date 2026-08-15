package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.service.user.MuserService;
import com.aye.entity.CommonColumn;
import com.aye.entity.user.Muser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Toufiq on 6/10/2019.
 */
@Service
public class CommonColumnServiceImpl {

    @Autowired
    private MuserService userService;

    public Muser getCurrentUser(String userName) {
//        CommonColumn commonColumn=new CommonColumn();
        Muser curUser = this.userService.findByUserName(userName);
//        if (curUser.getIs)
        return curUser;
    }

    public CommonColumn setCommonColumn(CommonColumn commonColumn, String mode, String userName) {

//        CommonColumn commonColumn=new CommonColumn();
        Muser curUser = this.userService.findByUserName(userName);
//        System.out.println(curUser.getId()+"curUser");
        if (mode.equals("E")) {
//            System.out.println("E");
            commonColumn.setCreatedBy(curUser);
            commonColumn.setCreationDate(new Date());
        } else {

//            System.out.println(commonColumn + "<--commonColumn-->" + curUser.getId());
            commonColumn.setLastUpdateBy(curUser.getId());
            commonColumn.setLastUpdateDate(new Date());


        }


        return commonColumn;
    }
}

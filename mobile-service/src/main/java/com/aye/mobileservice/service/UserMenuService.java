package com.aye.mobileservice.service;


import com.aye.commonlib.dto.request.UserMenuRequest;
import com.aye.commonlib.dto.request.UserSubMenuRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.mobileservice.feignclient.UserMenuFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMenuService {
    @Autowired
    UserMenuFeignClient controller;


    public ApiRequestResponse findByMenuId(Integer id) {
        return controller.findByMenuId(id).getBody();
    }


    public ApiRequestResponse findByMeuName(String menuName) {
        return controller.findByMeuName(menuName).getBody();
    }


    public ApiRequestResponse getUserAccess(String userName) {
        return controller.getUserAccess(userName).getBody();
    }


    public ApiRequestResponse getUserAccessNew(String userName) {
        return controller.getUserAccessNew(userName).getBody();
    }


    public ApiRequestResponse getAllManuName() {
        return controller.getAllManuName().getBody();
    }


    public ApiRequestResponse getAllMenu() {
        return controller.getAllMenu().getBody();
    }


    public ApiRequestResponse save(UserMenuRequest userMenuRequest) {
        return controller.save(userMenuRequest).getBody();
    }


    public ApiRequestResponse saveline(UserSubMenuRequest userSubMenuReq) {
        return controller.saveline(userSubMenuReq).getBody();
    }


    public ApiRequestResponse findByUser(String userName) {
        return controller.findByUser(userName).getBody();
    }


    public ApiRequestResponse findByMainMenu(Integer userMenuId) {
        return controller.findByMainMenu(userMenuId).getBody();
    }


    public ApiRequestResponse findBysubmId(Integer id) {
        return controller.findBysubmId(id).getBody();
    }


    public ApiRequestResponse findSubMenuById(Integer submenuId) {
        return controller.findSubMenuById(submenuId).getBody();
    }


    public ApiRequestResponse getUserAccessByUserName(String username,
                                                      String roleType) {
        return controller.getUserAccessByUserName(username, roleType).getBody();
    }


    public ApiRequestResponse findByPage(String pageName) {
        return controller.findByPage(pageName).getBody();
    }


    public ApiRequestResponse getUserReport(Integer temltDtId) {
        return controller.getUserReport(temltDtId).getBody();
    }
}

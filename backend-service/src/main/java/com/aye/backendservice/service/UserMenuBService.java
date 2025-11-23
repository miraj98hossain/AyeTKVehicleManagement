package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.UserMenuRequest;
import com.aye.commonlib.dto.request.UserSubMenuRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;


public interface UserMenuBService {
    ApiRequestResponse findByMenuId(Integer id);

    ApiRequestResponse findByMeuName(String menuName);

    ApiRequestResponse getUserAccess(String userName);

    ApiRequestResponse getUserAccessNew(String userName);

    ApiRequestResponse getAllManuName();

    ApiRequestResponse getAllMenu();

    ApiRequestResponse save(UserMenuRequest userMenuRequest) throws Exception;

    ApiRequestResponse saveline(UserSubMenuRequest userSubMenuReq);

    ApiRequestResponse findByUser(String userName);

    ApiRequestResponse findByMainMenu(Integer userMenuId);

    ApiRequestResponse findBysubmId(Integer id);

    ApiRequestResponse findSubMenuById(Integer submenuId);

    ApiRequestResponse getUserAccessByUserName(String username, String roleType);


    ApiRequestResponse findByPage(String pageName);

    ApiRequestResponse getUserReport(Integer temltDtId);
}

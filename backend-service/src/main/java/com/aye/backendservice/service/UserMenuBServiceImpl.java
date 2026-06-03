package com.aye.backendservice.service;


import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserMenuService;
import com.aye.backendservice.applicationEvent.UserMenusCacheSyncEvent;
import com.aye.dtoLib.dto.request.UserMenuRequest;
import com.aye.dtoLib.dto.request.UserSubMenuRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;
import com.aye.dtoLib.dto.response.userOrg.UserAccessResponse;
import com.aye.dtoLib.dto.response.userOrg.UserAccessTemltDtlResponse;
import com.aye.dtoLib.dto.response.userOrg.UserMenuResponse;
import com.aye.dtoLib.dto.response.userOrg.UserSubMenuResponse;
import com.aye.entitylib.entity.UserMenu;
import com.aye.entitylib.entity.UserSubMenu;
import com.aye.entitylib.entity.user.Muser;
import com.aye.mapper.userOrg.UserAccessMapper;
import com.aye.mapper.userOrg.UserAccessTemltDtlMapper;
import com.aye.mapper.userOrg.UserMenuMapper;
import com.aye.mapper.userOrg.UserSubMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserMenuBServiceImpl implements UserMenuBService {
    @Autowired
    private MuserService userService;
    @Autowired
    private UserMenuService menuService;
    @Autowired
    private UserAccessTemltDtlMapper userAccessTemltDtlMapper;
    @Autowired
    private UserMenuMapper userMenuMapper;
    @Autowired
    private UserSubMenuMapper userSubMenuMapper;
    @Autowired
    private UserAccessMapper userAccessMapper;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public ApiRequestResponse findByMenuId(Integer id) {
        UserMenuResponse userMenuRes = this.userMenuMapper
                .toResponseDto(this.menuService.findByMenuId(id));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "userMenu",
                UserMenuResponse.class.getName(), userMenuRes
        );
    }

    @Override
    public ApiRequestResponse findByMeuName(String menuName) {
        UserMenuResponse userMenuRes = this.userMenuMapper
                .toResponseDto(this.menuService.findByMeuName(menuName));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "userMenu",
                UserMenuResponse.class.getName(), userMenuRes
        );
    }

    @Override
    public ApiRequestResponse getUserAccess(String userName) {
        Muser muser = this.userService.findByUserName(userName);
        List<UserMenuResponse> userMenuResponseList = this.menuService.getUserAccess(muser)
                .stream().map(userMenuMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "userMenuResList",
                UserMenuResponse.class.getName(), userMenuResponseList
        );
    }

    @Override
    public ApiRequestResponse getUserAccessNew(String userName) {
        Muser muser = this.userService.findByUserName(userName);
        List<UserMenuResponse> userMenuResponseList = this.menuService.getUserAccessNew(muser)
                .stream().map(userMenuMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "userMenuResList",
                UserMenuResponse.class.getName(), userMenuResponseList
        );
    }

    @Override
    public ApiRequestResponse getAllManuName() {
        List<String> userMenuResponseList = this.menuService.getAllManuName();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "userMenuResList",
                String.class.getName(), userMenuResponseList
        );
    }

    @Override
    public ApiRequestResponse getAllMenu() {
        List<UserMenuResponse> userMenuResponseList = this.menuService.getAllMenu()
                .stream().map(userMenuMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "userManus",
                UserMenuResponse.class.getName(), userMenuResponseList
        );
    }

    @Transactional
    @Override
    public ApiRequestResponse save(UserMenuRequest userMenuRequest) throws Exception {
        UserMenu userMenu = this.userMenuMapper.dtoToEntity(userMenuRequest);
        userMenu = this.menuService.save(userMenu);
        this.eventPublisher.publishEvent(new UserMenusCacheSyncEvent(this, userMenu.getId()));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                null, null,
                null, null
        );
    }

    @Transactional
    @Override
    public ApiRequestResponse saveline(UserSubMenuRequest userSubMenuReq) {
        UserSubMenu userSubMenu = this.userSubMenuMapper.dtoToEntity(userSubMenuReq);
        UserMenu userMenu = this.menuService.findByMenuId(userSubMenuReq.getUserMenuId());
        userSubMenu.setUserMenu(userMenu);
        this.menuService.saveline(userSubMenu);
        this.eventPublisher.publishEvent(new UserMenusCacheSyncEvent(this, userMenu.getId()));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                null, null,
                null, null
        );
    }

    @Override
    public ApiRequestResponse findByUser(String userName) {
        Muser muser = this.userService.findByUserName(userName);
        List<UserAccessResponse> userAccessList = this.menuService.findByUser(muser)
                .stream().map(userAccessMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "userAccessList",
                UserAccessResponse.class.getName(), userAccessList
        );
    }

    @Override
    public ApiRequestResponse findByMainMenu(Integer userMenuId) {
        UserMenu userMenu = menuService.findByMenuId(userMenuId);
        List<UserSubMenuResponse> userSubMenuList = this.menuService.findByMainMenu(userMenu)
                .stream().map(userSubMenuMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "userSubMenuList",
                UserSubMenuResponse.class.getName(), userSubMenuList
        );
    }

    @Override
    public ApiRequestResponse findBysubmId(Integer id) {
        UserSubMenuResponse userSubMenuResponse = this.userSubMenuMapper
                .toResponseDto(this.menuService.findBysubmId(id));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "userSubMenu",
                UserSubMenuResponse.class.getName(), userSubMenuResponse
        );
    }

    @Override
    public ApiRequestResponse findSubMenuById(Integer submenuId) {
        UserSubMenuResponse userSubMenuResponse = this.userSubMenuMapper
                .toResponseDto(this.menuService.findSubMenuById(submenuId));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "userSubMenu",
                UserSubMenuResponse.class.getName(), userSubMenuResponse
        );
    }


    @Override
    public ApiRequestResponse findByPage(String pageName) {
        UserSubMenuResponse userSubMenuResponse = this.userSubMenuMapper
                .toResponseDto(this.menuService.findByPage(pageName));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "userSubMenu",
                UserSubMenuResponse.class.getName(), userSubMenuResponse
        );
    }

    @Override
    public ApiRequestResponse getUserReport(Integer temltDtId) {
        UserAccessTemltDtlResponse userAccessTemltDtlRes = this.userAccessTemltDtlMapper
                .toResponseDto(this.menuService.getUserReport(temltDtId));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "userSubMenu",
                UserAccessTemltDtlResponse.class.getName(), userAccessTemltDtlRes
        );
    }
}

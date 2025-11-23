package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.model.RoleTypes;
import com.aye.RestfulServer.model.UserMenu;
import com.aye.RestfulServer.model.UserSubMenu;
import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserMenuService;
import com.aye.backendservice.mapper.UserAccessMapper;
import com.aye.backendservice.mapper.UserAccessTemltDtlMapper;
import com.aye.backendservice.mapper.UserMenuMapper;
import com.aye.backendservice.mapper.UserSubMenuMapper;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.UserMenuBService;
import com.aye.commonlib.dto.request.UserMenuRequest;
import com.aye.commonlib.dto.request.UserSubMenuRequest;
import com.aye.commonlib.dto.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                ApiRequestResponseDetail.ObjectType.A, "manus",
                UserMenuResponse.class.getName(), userMenuResponseList
        );
    }

    @Override
    public ApiRequestResponse save(UserMenuRequest userMenuRequest) throws Exception {
        UserMenu userMenu = this.userMenuMapper.dtoToEntity(userMenuRequest);
        this.menuService.save(userMenu);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                null, null,
                null, null
        );
    }

    @Override
    public ApiRequestResponse saveline(UserSubMenuRequest userSubMenuReq) {
        UserSubMenu userSubMenu = this.userSubMenuMapper.dtoToEntity(userSubMenuReq);
        this.menuService.saveline(userSubMenu);
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
    public ApiRequestResponse getUserAccessByUserName(String username, String roleType) {
        Muser curUser = this.userService.findByUserName(username.toUpperCase());
        var list = this.menuService.getUserAccessNew1(curUser, RoleTypes.valueOf(roleType))
                .stream().map(userAccessTemltDtlMapper::toResponseDto).toList();
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Success");
        List<ApiRequestResponseDetail> detailsResList = new ArrayList<>();
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("manus")
                .object(list)
                .mapperClass(UserAccessTemltDtlResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.A)
                .build();
        detailsResList.add(details);
        response.setApiRequestResponseDetails(detailsResList);
        return response;
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

package com.aye.mobileservice.service;

import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.model.RoleTypes;
import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserMenuService;
import com.aye.commonlib.dto.response.UserAccessTemltDtlResponse;
import com.aye.mobileservice.mapper.UserAccessTemltDtlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private MuserService userService;
    @Autowired
    private UserMenuService menuService;
    @Autowired
    private UserAccessTemltDtlMapper userAccessTemltDtlMapper;

    public List<UserAccessTemltDtlResponse> getMenu(String username) {
        Muser curUser = this.userService.findByUserName(username.toUpperCase());

        return this.menuService.getUserAccessNew1(curUser, RoleTypes.ANDROID)
                .stream().map(userAccessTemltDtlMapper::toResponseDto).toList();
    }
}

package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.model.RoleTypes;
import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserMenuService;
import com.aye.backendservice.mapper.UserAccessTemltDtlMapper;
import com.aye.backendservice.service.UserMenuBService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.UserAccessTemltDtlResponse;
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
}

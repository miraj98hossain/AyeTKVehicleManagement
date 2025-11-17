package com.aye.mobileservice.service;

import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.model.RoleTypes;
import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserMenuService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.StepResponse;
import com.aye.mobileservice.mapper.UserAccessTemltDtlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private MuserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserMenuService menuService;
    @Autowired
    private UserAccessTemltDtlMapper userAccessTemltDtlMapper;

    public ApiRequestResponse login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        ApiRequestResponse response = new ApiRequestResponse();
        Muser curUser = this.userService.findByUserName(username.toUpperCase());
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Success");
        List<ApiRequestResponseDetail> detailsResList = new ArrayList<>();
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("userAccessTemltDtl")
                .object(this.menuService.getUserAccessNew1(curUser, RoleTypes.ANDROID)
                        .stream().map(userAccessTemltDtlMapper::toResponseDto).toList())
                .mapperClass(StepResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.A)
                .build();
        detailsResList.add(details);
        response.setApiRequestResponseDetails(detailsResList);

        return response;
    }
}

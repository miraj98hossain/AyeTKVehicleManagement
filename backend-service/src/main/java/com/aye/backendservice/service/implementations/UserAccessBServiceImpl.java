package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.model.UserAccess;
import com.aye.RestfulServer.model.UserAccessTemplt;
import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserAccessService;
import com.aye.RestfulServer.service.UserAccessTempltService;
import com.aye.backendservice.mapper.UserAccessMapper;
import com.aye.backendservice.mapper.UserAccessTempltMapper;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.UserAccessBService;
import com.aye.commonlib.dto.request.UserAccessRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.UserAccessResponse;
import com.aye.commonlib.dto.response.UserAccessTempltResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccessBServiceImpl implements UserAccessBService {
    @Autowired
    private UserAccessTempltService userAccessTempltService;
    @Autowired
    private MuserService mUserService;
    @Autowired
    private UserAccessTempltMapper userAccessTempltMapper;
    @Autowired
    private UserAccessMapper userAccessMapper;
    @Autowired
    private UserAccessService userAccessService;

    @Override
    public ApiRequestResponse getAllTemplet() {
        List<UserAccessTempltResponse> list = this.userAccessTempltService.getAllTemplet()
                .stream().map(userAccessTempltMapper::toResponseDto).toList();

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched All UserAccessTemplate",
                ApiRequestResponseDetail.ObjectType.A, "AllTemplate",
                UserAccessTempltResponse.class.getName(), list
        );
    }

    @Override
    public ApiRequestResponse findByUserId(Integer userId) {
        List<UserAccessResponse> userAccessesRes = this.userAccessService.findByUserId(userId)
                .stream().map(userAccessMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched All UserAccess",
                ApiRequestResponseDetail.ObjectType.A, "AllUserAccess",
                UserAccessResponse.class.getName(), userAccessesRes
        );
    }

    @Override
    public ApiRequestResponse saveDtlLine(UserAccessRequest userAccessRequest) {
        UserAccess userAccess = this.userAccessMapper.dtoToUserAccess(userAccessRequest);
        Muser muser = this.mUserService.findById(userAccessRequest.getUserId());
        UserAccessTemplt userAccessTemplt = this.userAccessTempltService.findById(userAccessRequest.getUserAccessTempltId());
        userAccess.setUser(muser);
        userAccess.setUserAccessTemplt(userAccessTemplt);
        this.userAccessService.saveDtlLine(userAccess);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Created UserAccess",
                ApiRequestResponseDetail.ObjectType.O, "",
                null, null
        );
    }
}

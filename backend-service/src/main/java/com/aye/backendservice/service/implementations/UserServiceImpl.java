package com.aye.backendservice.service.implementations;


import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.model.MuserDto;
import com.aye.RestfulServer.service.MuserService;
import com.aye.backendservice.mapper.MRoleMapper;
import com.aye.backendservice.mapper.MUserMapper;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.UserService;
import com.aye.commonlib.dto.request.MUserRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.MRoleResponse;
import com.aye.commonlib.dto.response.MUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MuserService muserService;
    @Autowired
    private MUserMapper mUserMapper;
    @Autowired
    private MRoleMapper mRoleMapper;

    @Override
    public ApiRequestResponse findByUserName(String username) {

        ApiRequestResponse response = new ApiRequestResponse();
        Muser curUser = this.muserService.findByUserName(username.toUpperCase());
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Success");
        List<ApiRequestResponseDetail> detailsResList = new ArrayList<>();
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("mUserResponse")
                .object(mUserMapper.toResponseDto(curUser))
                .mapperClass(MUserResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.O)
                .build();
        detailsResList.add(details);
        response.setApiRequestResponseDetails(detailsResList);

        return response;
    }

    @Override
    public ApiRequestResponse findByUserNameLike(String username) {
        List<MUserResponse> mUserList = this.muserService.findByUserNameLike(username)
                .stream().map(mUser -> mUserMapper.toResponseDto(mUser)).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "mUsers",
                MUserResponse.class.getName(), mUserList
        );
    }

    @Override
    public ApiRequestResponse findAllUser() {
        List<MuserDto> mUserList = this.muserService.findAllUser();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "mUsers",
                MuserDto.class.getName(), mUserList
        );
    }

    @Override
    public ApiRequestResponse findById(Integer userId) {
        MUserResponse nUser = mUserMapper.toResponseDto(this.muserService.findById(userId));

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "editUser",
                MUserResponse.class.getName(), nUser
        );
    }

    @Override
    public ApiRequestResponse findAllRoles() {
        List<MRoleResponse> nUser = this.muserService.findAllRoles()
                .stream().map(mRoleMapper::toResponseDto).toList();

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "userRole",
                MRoleResponse.class.getName(), nUser
        );
    }

    @Override
    public ApiRequestResponse updateUser(MUserRequest mUserRequest) {
        Muser muser = mUserMapper.dtoToEntity(mUserRequest);
        this.muserService.updateUser(muser);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Updated User",
                ApiRequestResponseDetail.ObjectType.O, "",
                MRoleResponse.class.getName(), null
        );
    }
}


package com.aye.backendservice.service.implementations;


import com.aye.RestfulServer.model.CommonColumn;
import com.aye.RestfulServer.model.Mrole;
import com.aye.RestfulServer.model.Muser;
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
import java.util.Date;
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
                .objectTag("mUser")
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
        List<MUserResponse> mUserList = this.muserService.findAllUsers()
                .stream().map(mUserMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "mUsers",
                MUserResponse.class.getName(), mUserList
        );
    }

    @Override
    public ApiRequestResponse findById(Integer userId) {
        MUserResponse nUser = mUserMapper.toResponseDto(this.muserService.findById(userId));

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "mUser",
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
    public ApiRequestResponse updateUser(MUserRequest mUserRequest, String username) {
        Mrole mrole = this.muserService.findRoleById(mUserRequest.getRoleId());
        Muser currentMuser = this.muserService.findByUserName(username.toUpperCase());
        if (mUserRequest.getId() != null) {
            Muser reqUser = this.muserService.findById(mUserRequest.getId());
            if (!reqUser.getPassword().equals(mUserRequest.getPassword())) {
                reqUser.setIsPassChange(true);
            }
            reqUser.setPassword(mUserRequest.getPassword());
            reqUser.setName(mUserRequest.getName());
            reqUser.setUserName(mUserRequest.getUserName().toUpperCase());
            reqUser.setLastName(mUserRequest.getLastName());
            reqUser.getRoles().add(mrole);
            reqUser.setEnabled(mUserRequest.isEnabled());
            reqUser.setUserType(Muser.UserType.valueOf(mUserRequest.getUserType()));
            var cc = reqUser.getColumn();
            cc.setLastUpdateBy(currentMuser.getId());
            cc.setLastUpdateDate(new Date());
            reqUser.setColumn(cc);
            this.muserService.updateUser(reqUser);
            return ApiRequestResponseMaker.make(
                    HttpStatus.OK.name(), "Successfully Updated User",
                    ApiRequestResponseDetail.ObjectType.O, "",
                    MRoleResponse.class.getName(), null
            );
        }

        Muser reqUser = this.mUserMapper.dtoToEntity(mUserRequest);
        reqUser.getRoles().add(mrole);
        CommonColumn commonColumn = new CommonColumn();
        commonColumn.setCreatedBy(currentMuser);
        commonColumn.setCreationDate(new Date());
        reqUser.setColumn(commonColumn);
        this.muserService.updateUser(reqUser);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Created User",
                ApiRequestResponseDetail.ObjectType.O, "",
                MRoleResponse.class.getName(), null
        );
    }
}


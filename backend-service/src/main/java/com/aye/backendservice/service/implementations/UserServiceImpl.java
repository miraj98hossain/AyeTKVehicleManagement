package com.aye.backendservice.service.implementations;


import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.service.MuserService;
import com.aye.backendservice.mapper.MUserMapper;
import com.aye.backendservice.service.UserService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.MUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    MuserService muserService;
    @Autowired
    MUserMapper mUserMapper;

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
}


package com.aye.backendservice.service;

import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.model.UserCodeAccess;
import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserCodeAccessService;
import com.aye.backendservice.mapper.UserCodeAccessMapper;
import com.aye.commonlib.dto.request.UserCodeAccessRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.UserCodeAccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCodeAccessBServiceImpl implements UserCodeAccessBService {
    @Autowired
    MuserService userMuserService;
    @Autowired
    UserCodeAccessService userCodeAccessService;
    @Autowired
    UserCodeAccessMapper userCodeAccessMapper;

    @Override
    public ApiRequestResponse save(UserCodeAccessRequest userCodeAccessRequest, String currentUser) {
        Muser muser = this.userMuserService.findByUserName(currentUser);

        if (userCodeAccessRequest.getId() != null) {
            var dbUserCodeAccess = userCodeAccessService.findById(userCodeAccessRequest.getId());
            userCodeAccessMapper.dtoToEntity(userCodeAccessRequest, dbUserCodeAccess);
            dbUserCodeAccess.setLastUpdateBy(muser.getId());
            userCodeAccessService.save(dbUserCodeAccess);
            return ApiRequestResponseMaker.make(
                    HttpStatus.OK.name(),
                    "Success",
                    ApiRequestResponseDetail.ObjectType.O,
                    "userCodeAccess",
                    UserCodeAccessResponse.class.getName(),
                    userCodeAccessMapper.toResponseDto(dbUserCodeAccess)
            );
        }
        UserCodeAccess userCodeAccess = this.userCodeAccessMapper.dtoToEntity(userCodeAccessRequest);
        userCodeAccess.setCreatedBy(muser.getId());
        userCodeAccess = userCodeAccessService.save(userCodeAccess);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "userCodeAccess",
                UserCodeAccessResponse.class.getName(),
                userCodeAccessMapper.toResponseDto(userCodeAccess)
        );
    }

    @Override
    public ApiRequestResponse findById(Long userCodeAccessId) {
        UserCodeAccess userCodeAccess = userCodeAccessService.findById(userCodeAccessId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "userCodeAccess",
                UserCodeAccessResponse.class.getName(),
                userCodeAccessMapper.toResponseDto(userCodeAccess)
        );
    }

    @Override
    public ApiRequestResponse findAllByUser(Integer userId) {
        List<UserCodeAccess> userCodeAccess = userCodeAccessService.findAllByUser(userId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "userCodeAccess",
                UserCodeAccessResponse.class.getName(),
                userCodeAccess.stream().map(this.userCodeAccessMapper::toResponseDto).toList()
        );
    }
}

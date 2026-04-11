package com.aye.backendservice.service;

import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserCodeAccessService;
import com.aye.dtoLib.dto.request.UserCodeAccessRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;
import com.aye.dtoLib.dto.response.userOrg.UserCodeAccessResponse;
import com.aye.entitylib.entity.UserCodeAccess;
import com.aye.entitylib.entity.user.Muser;
import com.aye.mapper.userOrg.UserCodeAccessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCodeAccessBServiceImpl implements UserCodeAccessBService {
    @Autowired
    private MuserService userMuserService;
    @Autowired
    private UserCodeAccessService userCodeAccessService;
    @Autowired
    private UserCodeAccessMapper userCodeAccessMapper;

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

    @Override
    public ApiRequestResponse findAllByUser(String userName) {
        List<UserCodeAccess> userCodeAccess = userCodeAccessService.findAllByUser(userName);
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

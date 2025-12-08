package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.model.UserCodeAccess;
import com.aye.RestfulServer.repo.UserCodeAccessRepo;
import com.aye.backendservice.mapper.UserCodeAccessMapper;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.UserCodeAccessBService;
import com.aye.commonlib.dto.request.UserCodeAccessRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.UserCodeAccessResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCodeAccessBServiceImpl implements UserCodeAccessBService {
    @Autowired
    UserCodeAccessRepo userCodeAccessRepo;
    @Autowired
    UserCodeAccessMapper userCodeAccessMapper;

    @Override
    public ApiRequestResponse save(UserCodeAccessRequest userCodeAccessRequest) {
        UserCodeAccess userCodeAccess = this.userCodeAccessMapper.dtoToEntity(userCodeAccessRequest);
        userCodeAccess = userCodeAccessRepo.save(userCodeAccess);
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
        UserCodeAccess userCodeAccess = userCodeAccessRepo.findById(userCodeAccessId).orElseThrow(
                () -> new EntityNotFoundException("UserCodeAccess with id: " + userCodeAccessId + " not found")
        );
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
        List<UserCodeAccess> userCodeAccess = userCodeAccessRepo.findByUser_Id(userId);
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

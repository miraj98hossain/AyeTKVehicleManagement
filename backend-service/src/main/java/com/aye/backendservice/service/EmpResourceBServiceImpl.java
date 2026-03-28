package com.aye.backendservice.service;


import com.aye.RestfulServer.service.CustomerResourceService;

import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;

import com.aye.dtoLib.dto.response.userData.EmpResourceResDto;
import com.aye.mapper.user.EmpResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpResourceBServiceImpl implements EmpResourceBService {
    @Autowired
    CustomerResourceService customerResourceService;
    @Autowired
    EmpResourceMapper empResMapper;

    @Override
    public ApiRequestResponse findEmpResourceByOrgId(Long orgId) {
        List<EmpResourceResDto> empRes = customerResourceService.findEmpResourceByOrgId(orgId)
                .stream().map(empResMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "empResourceList",
                EmpResourceResDto.class.getName(), empRes
        );
    }
}

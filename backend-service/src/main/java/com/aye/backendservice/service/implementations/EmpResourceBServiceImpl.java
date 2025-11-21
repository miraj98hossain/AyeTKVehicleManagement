package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.service.CustomerResourceService;
import com.aye.backendservice.mapper.EmpResourceMapper;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.EmpResourceBService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.EmpResourceResponse;
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
        List<EmpResourceResponse> empRes = customerResourceService.findEmpResourceByOrgId(orgId)
                .stream().map(empResMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "empResourceList",
                EmpResourceResponse.class.getName(), empRes
        );
    }
}

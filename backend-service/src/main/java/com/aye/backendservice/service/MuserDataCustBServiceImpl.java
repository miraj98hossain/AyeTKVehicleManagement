package com.aye.backendservice.service;

import com.aye.RestfulServer.service.MuserDataCustService;

import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;

import com.aye.dtoLib.dto.response.userData.MuserDataCustResDto;
import com.aye.mapper.user.MuserDataCustMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuserDataCustBServiceImpl implements MuserDataCustBService {

    @Autowired
    private MuserDataCustService muserDataCustService;
    @Autowired
    private MuserDataCustMapper muserDataCustMapper;

    @Override
    public ApiRequestResponse getAllCustomerByOrg(Long orgId) {
        List<MuserDataCustResDto> muserDataCustList = this.muserDataCustService.getAllCustomerByOrg(orgId)
                .stream().map(this.muserDataCustMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "muserDataCustList",
                MuserDataCustResDto.class.getName(), muserDataCustList
        );
    }
}

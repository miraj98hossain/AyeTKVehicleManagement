package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.service.MuserDataCustService;
import com.aye.backendservice.mapper.MuserDataCustMapper;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.MuserDataCustBService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.MuserDataCustResponse;
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
        List<MuserDataCustResponse> muserDataCustList = this.muserDataCustService.getAllCustomerByOrg(orgId)
                .stream().map(this.muserDataCustMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "muserDataCustList",
                MuserDataCustResponse.class.getName(), muserDataCustList
        );
    }
}

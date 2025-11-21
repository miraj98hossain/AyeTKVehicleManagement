package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.model.RequestDtlSearch;
import com.aye.RestfulServer.service.RequestGroupHeaderService;
import com.aye.backendservice.mapper.RequestGroupHeaderMapper;
import com.aye.backendservice.mapper.RequestGroupLineMapper;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.ReqGrpHeaderBService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.RequestGroupHeaderResponse;
import com.aye.commonlib.dto.response.RequestGroupLineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReqGrpHeaderBServiceImpl implements ReqGrpHeaderBService {
    @Autowired
    RequestGroupHeaderService reqGrpHeaderService;
    @Autowired
    RequestGroupHeaderMapper reqGrpHeaderMapper;
    @Autowired
    RequestGroupLineMapper reqGrpHeaderLineMapper;

    @Override
    public ApiRequestResponse findAll() {
        List<RequestGroupHeaderResponse> reqGrpHeaders = reqGrpHeaderService.findAll()
                .stream().map(reqGrpHeaderMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "requestGroupHeadersL",
                RequestGroupHeaderResponse.class.getName(), reqGrpHeaders
        );
    }

    @Override
    public ApiRequestResponse getAllLines() {
        List<RequestGroupLineResponse> reqGrpHeaders = reqGrpHeaderService.getAll()
                .stream().map(reqGrpHeaderLineMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "requestGroupLinesL",
                RequestGroupLineResponse.class.getName(), reqGrpHeaders
        );
    }

    @Override
    public ApiRequestResponse findByRequestHdr(RequestDtlSearch search) {
        List<RequestGroupLineResponse> reqGrpHeaders = reqGrpHeaderService.findByRequestHdr(search)
                .stream().map(reqGrpHeaderLineMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "requestGroupLinesL",
                RequestGroupLineResponse.class.getName(), reqGrpHeaders
        );
    }

    @Override
    public ApiRequestResponse findLineById(Long lineId) {
        RequestGroupLineResponse reqGrpHeader = reqGrpHeaderLineMapper
                .toResponseDto(this.reqGrpHeaderService.findByLineId(lineId));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "requestGroupLine",
                RequestGroupLineResponse.class.getName(), reqGrpHeader
        );
    }
}

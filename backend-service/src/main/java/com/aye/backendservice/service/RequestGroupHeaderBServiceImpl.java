package com.aye.backendservice.service;

import com.aye.RestfulServer.model.RequestGroupHeader;
import com.aye.RestfulServer.model.RequestGroupLine;
import com.aye.RestfulServer.service.RequestGroupHeaderService;
import com.aye.backendservice.mapper.RequestGroupHeaderMapper;
import com.aye.backendservice.mapper.RequestGroupLineMapper;
import com.aye.commonlib.dto.request.RequestGroupHeaderRequest;
import com.aye.commonlib.dto.request.RequestGroupLineRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.RequestGroupHeaderResponse;
import com.aye.commonlib.dto.response.RequestGroupLineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestGroupHeaderBServiceImpl implements RequestGroupHeaderBService {
    @Autowired
    RequestGroupHeaderService reqGrpHdrService;
    @Autowired
    RequestGroupHeaderMapper reqGrpHdrMapper;
    @Autowired
    RequestGroupLineMapper reqGrpLineMapper;

    @Override
    public ApiRequestResponse findAllRequestGroup() {
        List<RequestGroupHeaderResponse> requestGrpHeaderRes = reqGrpHdrService.findAll()
                .stream().map(this.reqGrpHdrMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "requestGroupList",
                RequestGroupHeaderResponse.class.getName(), requestGrpHeaderRes
        );
    }

    @Override
    public ApiRequestResponse findReqGrpById(Long id) {
        RequestGroupHeaderResponse response = this.reqGrpHdrMapper
                .toResponseDto(reqGrpHdrService.findById(id));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "requestGroup",
                RequestGroupHeaderResponse.class.getName(), response
        );
    }

    @Override
    public ApiRequestResponse saveReqGrp(RequestGroupHeaderRequest reqGrpHdrReq) {
        RequestGroupHeader requestGroupHeader = this.reqGrpHdrMapper.dtoToEntity(reqGrpHdrReq);
        this.reqGrpHdrService.save(requestGroupHeader);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                null, null,
                null, null
        );
    }

    @Override
    public ApiRequestResponse getUserReport(Long reqGrpId) {
        RequestGroupHeaderResponse response = this.reqGrpHdrMapper
                .toResponseDto(reqGrpHdrService.getUserReport(reqGrpId));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "requestGroup",
                RequestGroupHeaderResponse.class.getName(), response
        );
    }

    @Override
    public ApiRequestResponse saveReqGrpLine(RequestGroupLineRequest reqGrpLnReq) {
        RequestGroupHeader requestGroupHeader = this.reqGrpHdrService.findById(reqGrpLnReq.getRequestGroupHeaderId());
        RequestGroupLine requestGroupLine = this.reqGrpLineMapper.dtoToEntity(reqGrpLnReq);
        requestGroupLine.setRequestGroupHeader(requestGroupHeader);
        this.reqGrpHdrService.linesave(requestGroupLine);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                null, null,
                null, null
        );
    }

    @Override
    public ApiRequestResponse getAllLines() {
        List<RequestGroupLineResponse> requestGrpHeaderRes = reqGrpHdrService.getAll()
                .stream().map(this.reqGrpLineMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "requestGroupLineList",
                RequestGroupLineResponse.class.getName(), requestGrpHeaderRes
        );
    }

    @Override
    public ApiRequestResponse findByLineId(Long id) {
        RequestGroupLineResponse response = this.reqGrpLineMapper
                .toResponseDto(reqGrpHdrService.findByLineId(id));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "requestGroupLine",
                RequestGroupLineResponse.class.getName(), response
        );
    }

    @Override
    public ApiRequestResponse findByrequestGroupHeader(Long reqGrpHdrId) {
        RequestGroupHeader requestGroupHeader = this.reqGrpHdrService.findById(reqGrpHdrId);
        List<RequestGroupLineResponse> requestGroupLineList = this.reqGrpHdrService.findByrequestGroupHeader(requestGroupHeader)
                .stream().map(this.reqGrpLineMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "requestGroup",
                RequestGroupLineResponse.class.getName(), requestGroupLineList
        );

    }

    @Override
    public ApiRequestResponse findActiveByRequestGroupHeader(Long reqGrpHdrId) {
        RequestGroupHeader requestGroupHeader = this.reqGrpHdrService.findById(reqGrpHdrId);
        List<RequestGroupLineResponse> requestGroupLineList = this.reqGrpHdrService.findActiveByRequestGroupHeader(requestGroupHeader)
                .stream().map(this.reqGrpLineMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "requestGroup",
                RequestGroupLineResponse.class.getName(), requestGroupLineList
        );
    }


    @Override
    public ApiRequestResponse findByExecutables(Long executablesId) {
        List<RequestGroupLineResponse> requestGroupLineList = this.reqGrpHdrService.findByExecutables(executablesId)
                .stream().map(this.reqGrpLineMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "requestGroup",
                RequestGroupLineResponse.class.getName(), requestGroupLineList
        );
    }
}

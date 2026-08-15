//package com.aye.backendservice.service;
//
//
//import com.aye.dto.response.ApiRequestResponse;
//import com.aye.dto.response.ApiRequestResponseDetail;
//import com.aye.dto.response.user.RequestGroupHeaderDto;
//import com.aye.dto.response.user.RequestGroupLineDto;
//import com.aye.entity.RequestGroupHeader;
//import com.aye.entity.RequestGroupLine;
//import com.aye.mapper.concurrency.RequestGroupHeaderMapper;
//import com.aye.mapper.concurrency.RequestGroupLineMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class RequestGroupHeaderBServiceImpl implements RequestGroupHeaderBService {
//    @Autowired
//    RequestGroupHeaderService reqGrpHdrService;
//    @Autowired
//    RequestGroupHeaderMapper reqGrpHdrMapper;
//    @Autowired
//    RequestGroupLineMapper reqGrpLineMapper;
//
//    @Override
//    public ApiRequestResponse findAllRequestGroup() {
//        List<RequestGroupHeaderDto> requestGrpHeaderRes = reqGrpHdrService.findAll()
//                .stream().map(this.reqGrpHdrMapper::toResponseDto).toList();
//        return ApiRequestResponseMaker.make(
//                HttpStatus.OK.name(), "Success",
//                ApiRequestResponseDetail.ObjectType.A, "requestGroupList",
//                RequestGroupHeaderDto.class.getName(), requestGrpHeaderRes
//        );
//    }
//
//    @Override
//    public ApiRequestResponse findReqGrpById(Long id) {
//        RequestGroupHeaderDto response = this.reqGrpHdrMapper
//                .toResponseDto(reqGrpHdrService.findById(id));
//        return ApiRequestResponseMaker.make(
//                HttpStatus.OK.name(), "Success",
//                ApiRequestResponseDetail.ObjectType.O, "requestGroup",
//                RequestGroupHeaderDto.class.getName(), response
//        );
//    }
//
//    @Override
//    public ApiRequestResponse saveReqGrp(RequestGroupHeaderDto reqGrpHdrReq) {
//        RequestGroupHeader requestGroupHeader = this.reqGrpHdrMapper.toEntity(reqGrpHdrReq);
//        this.reqGrpHdrService.save(requestGroupHeader);
//        return ApiRequestResponseMaker.make(
//                HttpStatus.OK.name(), "Success",
//                null, null,
//                null, null
//        );
//    }
//
//    @Override
//    public ApiRequestResponse getUserReport(Long reqGrpId) {
//        RequestGroupHeaderDto response = this.reqGrpHdrMapper
//                .toResponseDto(reqGrpHdrService.getUserReport(reqGrpId));
//        return ApiRequestResponseMaker.make(
//                HttpStatus.OK.name(), "Success",
//                ApiRequestResponseDetail.ObjectType.O, "requestGroup",
//                RequestGroupHeaderDto.class.getName(), response
//        );
//    }
//
//    @Override
//    public ApiRequestResponse saveReqGrpLine(RequestGroupLineDto reqGrpLnReq) {
//        RequestGroupHeader requestGroupHeader = this.reqGrpHdrService.findById(reqGrpLnReq.getRequestGroupHeader().getId());
//        RequestGroupLine requestGroupLine = this.reqGrpLineMapper.toEntity(reqGrpLnReq);
//        requestGroupLine.setRequestGroupHeader(requestGroupHeader);
//        this.reqGrpHdrService.linesave(requestGroupLine);
//        return ApiRequestResponseMaker.make(
//                HttpStatus.OK.name(), "Success",
//                null, null,
//                null, null
//        );
//    }
//
//    @Override
//    public ApiRequestResponse getAllLines() {
//        List<RequestGroupLineDto> requestGrpHeaderRes = reqGrpHdrService.getAll()
//                .stream().map(this.reqGrpLineMapper::toResponseDto).toList();
//        return ApiRequestResponseMaker.make(
//                HttpStatus.OK.name(), "Success",
//                ApiRequestResponseDetail.ObjectType.A, "requestGroupLineList",
//                RequestGroupLineDto.class.getName(), requestGrpHeaderRes
//        );
//    }
//
//    @Override
//    public ApiRequestResponse findByLineId(Long id) {
//        RequestGroupLineDto response = this.reqGrpLineMapper
//                .toResponseDto(reqGrpHdrService.findByLineId(id));
//        return ApiRequestResponseMaker.make(
//                HttpStatus.OK.name(), "Success",
//                ApiRequestResponseDetail.ObjectType.O, "requestGroupLine",
//                RequestGroupLineDto.class.getName(), response
//        );
//    }
//
//    @Override
//    public ApiRequestResponse findByrequestGroupHeader(Long reqGrpHdrId) {
//        RequestGroupHeader requestGroupHeader = this.reqGrpHdrService.findById(reqGrpHdrId);
//        List<RequestGroupLineDto> requestGroupLineList = this.reqGrpHdrService.findByrequestGroupHeader(requestGroupHeader)
//                .stream().map(this.reqGrpLineMapper::toResponseDto).toList();
//        return ApiRequestResponseMaker.make(
//                HttpStatus.OK.name(), "Success",
//                ApiRequestResponseDetail.ObjectType.O, "requestGroup",
//                RequestGroupLineDto.class.getName(), requestGroupLineList
//        );
//
//    }
//
//    @Override
//    public ApiRequestResponse findActiveByRequestGroupHeader(Long reqGrpHdrId) {
//        RequestGroupHeader requestGroupHeader = this.reqGrpHdrService.findById(reqGrpHdrId);
//        List<RequestGroupLineDto> requestGroupLineList = this.reqGrpHdrService.findActiveByRequestGroupHeader(requestGroupHeader)
//                .stream().map(this.reqGrpLineMapper::toResponseDto).toList();
//        return ApiRequestResponseMaker.make(
//                HttpStatus.OK.name(), "Success",
//                ApiRequestResponseDetail.ObjectType.A, "requestGroup",
//                RequestGroupLineDto.class.getName(), requestGroupLineList
//        );
//    }
//
//
//    @Override
//    public ApiRequestResponse findByExecutables(Long executablesId) {
//        List<RequestGroupLineDto> requestGroupLineList = this.reqGrpHdrService.findByExecutables(executablesId)
//                .stream().map(this.reqGrpLineMapper::toResponseDto).toList();
//        return ApiRequestResponseMaker.make(
//                HttpStatus.OK.name(), "Success",
//                ApiRequestResponseDetail.ObjectType.A, "requestGroup",
//                RequestGroupLineDto.class.getName(), requestGroupLineList
//        );
//    }
//}

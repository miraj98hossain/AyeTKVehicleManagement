package com.aye.backendservice.service.concurrency;

import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.ApiRequestResponseDetail;
import com.aye.dto.response.user.RequestGroupHeaderDto;
import com.aye.dto.response.user.RequestGroupLineDto;
import com.aye.mapper.concurrency.RequestGroupHeaderMapper;
import com.aye.mapper.concurrency.RequestGroupLineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Miraj
 * @date: 04/07/2026
 * @time: 4:29 pm
 */
@Service
@RequiredArgsConstructor
public class RequestGroupViewService {
    private final RequestGroupService requestGroupService;
    private final RequestGroupHeaderMapper requestGroupHeaderMapper;
    private final RequestGroupLineMapper requestGroupLineMapper;

    public ApiRequestResponse getAllRequestGroupHeader() {
        List<RequestGroupHeaderDto> headerList = this.requestGroupService.findAll();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "requestGroupList",
                RequestGroupHeaderDto.class.getName(), headerList

        );
    }

    public ApiRequestResponse getRequestGroupHeaderById(Long headerId) {
        RequestGroupHeaderDto requestGroupHeaderDto = this.requestGroupService.findByRequestGroupId(headerId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "requestGroupHeader",
                RequestGroupHeaderDto.class.getName(), requestGroupHeaderDto

        );
    }


    public ApiRequestResponse saveRequestGroupHeader(RequestGroupHeaderDto requestDto, String userName) {
        RequestGroupHeaderDto requestGroupHeaderDto = this.requestGroupService.saveRequestGroup(requestDto, userName);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "requestGroupHeader",
                RequestGroupHeaderDto.class.getName(), requestGroupHeaderDto

        );
    }


    public ApiRequestResponse getRequestGroupLineById(Long lineId) {
        RequestGroupLineDto requestGroupLineDto = this.requestGroupService.findByRequestLineId(lineId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "requestGroupLine",
                RequestGroupLineDto.class.getName(), requestGroupLineDto

        );
    }


    public ApiRequestResponse getAllRequestGroupLineByHeaderId(Long headerId) {
        List<RequestGroupLineDto> requestGroupLineDto = this.requestGroupService.findByRequestGroupHeaderId(headerId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "requestGroupLines",
                RequestGroupLineDto.class.getName(), requestGroupLineDto

        );
    }


    public ApiRequestResponse saveRequestGroupLine(RequestGroupLineDto requestDto, String userName) {
        RequestGroupLineDto requestGroupLineDto = this.requestGroupService.saveRequestGroupLine(requestDto, userName);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "requestGroupLine",
                RequestGroupLineDto.class.getName(), requestGroupLineDto

        );
    }
}

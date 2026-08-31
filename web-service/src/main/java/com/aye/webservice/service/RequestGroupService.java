package com.aye.webservice.service;

import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.user.RequestGroupHeaderDto;
import com.aye.dto.response.user.RequestGroupLineDto;
import com.aye.webservice.feignclient.RequestGroupFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 20/08/2026
 * @time: 9:59 am
 */
@Service
@RequiredArgsConstructor
public class RequestGroupService {
    private final RequestGroupFeignClient reqGrpFeignClient;


    public ApiRequestResponse getAllRequestGroupHeader() {
        return this.reqGrpFeignClient.getAllRequestGroupHeader().getBody();
    }

    public ApiRequestResponse getRequestGroupHeaderById(Long headerId) {
        return this.reqGrpFeignClient.getRequestGroupHeaderById(headerId).getBody();
    }

    public ApiRequestResponse saveRequestGroupHeader(RequestGroupHeaderDto requestGroupHeaderDto, String userName) {
        return this.reqGrpFeignClient.saveRequestGroupHeader(requestGroupHeaderDto, userName).getBody();
    }

    // not Api response
    public ApiRequestResponse getRequestGroupLineById(Long lineId) {
        return this.reqGrpFeignClient.getRequestGroupLineById(lineId).getBody();
    }

    public ApiRequestResponse getAllRequestGroupLineByHeaderId(Long headerId) {
        return this.reqGrpFeignClient.getAllRequestGroupLineByHeaderId(headerId).getBody();
    }

    public ApiRequestResponse saveRequestGroupLine(RequestGroupLineDto requestGroupLineDto, String userName) {
        return this.reqGrpFeignClient.saveRequestGroupLine(requestGroupLineDto, userName).getBody();
    }
}

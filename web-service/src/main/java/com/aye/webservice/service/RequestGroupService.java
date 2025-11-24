package com.aye.webservice.service;

import com.aye.commonlib.dto.request.RequestGroupHeaderRequest;
import com.aye.commonlib.dto.request.RequestGroupLineRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.RequestGroupFeignClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RequestGroupService {
    @Autowired
    private RequestGroupFeignClient reqGrpFgnClient;


    public ApiRequestResponse findAllRequestGroup() {
        return this.reqGrpFgnClient.findAllRequestGroup().getBody();
    }


    public ApiRequestResponse findReqGrpById(@PathVariable("id") Long id) {
        return this.reqGrpFgnClient.findReqGrpById(id).getBody();
    }


    public ApiRequestResponse saveReqGrp(@Valid @RequestBody
                                         RequestGroupHeaderRequest requestGroupHeader) {
        return this.reqGrpFgnClient.saveReqGrp(requestGroupHeader).getBody();
    }


    public ApiRequestResponse getUserReport(Long reqGrpId) {
        return this.reqGrpFgnClient.getUserReport(reqGrpId).getBody();
    }


    public ApiRequestResponse saveReqGrpLine(RequestGroupLineRequest requestGroupLine) {
        return this.reqGrpFgnClient.saveReqGrpLine(requestGroupLine).getBody();
    }


    public ApiRequestResponse getAllLines() {
        return this.reqGrpFgnClient.getAllLines().getBody();
    }


    public ApiRequestResponse findByLineId(Long id) {
        return this.reqGrpFgnClient.findByLineId(id).getBody();
    }


    public ApiRequestResponse findByrequestGroupHeader(
            Long reqGrpHdrId) {
        return this.reqGrpFgnClient.findByrequestGroupHeader(reqGrpHdrId).getBody();
    }


    public ApiRequestResponse findActiveByRequestGroupHeader(
            Long reqGrpHdrId) {
        return this.reqGrpFgnClient.findActiveByRequestGroupHeader(reqGrpHdrId).getBody();
    }


    public ApiRequestResponse findByExecutables(
            Long executablesId) {
        return this.reqGrpFgnClient.findByExecutables(executablesId).getBody();
    }
}

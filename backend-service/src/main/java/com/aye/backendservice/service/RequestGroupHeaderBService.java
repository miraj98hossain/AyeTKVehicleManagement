package com.aye.backendservice.service;

import com.aye.dtoLib.dto.request.RequestGroupHeaderRequest;
import com.aye.dtoLib.dto.request.RequestGroupLineRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;

public interface RequestGroupHeaderBService {
    ApiRequestResponse findAllRequestGroup();

    ApiRequestResponse findReqGrpById(Long id);

    ApiRequestResponse saveReqGrp(RequestGroupHeaderRequest reqGrpHdrReq);

    ApiRequestResponse getUserReport(Long reqGrpId);

    ApiRequestResponse saveReqGrpLine(RequestGroupLineRequest reqGrpLnReq);

    ApiRequestResponse getAllLines();

    ApiRequestResponse findByLineId(Long id);

    ApiRequestResponse findByrequestGroupHeader(Long reqGrpHdrId);

    ApiRequestResponse findActiveByRequestGroupHeader(Long reqGrpHdrId);

//    ApiRequestResponse findByRequestHdr(RequestDtlSearch requestDtlSearch);

    ApiRequestResponse findByExecutables(Long executablesId);
}

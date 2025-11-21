package com.aye.backendservice.service;

import com.aye.RestfulServer.model.RequestDtlSearch;
import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface ReqGrpHeaderBService {
    ApiRequestResponse findAll();

    ApiRequestResponse getAllLines();

    ApiRequestResponse findByRequestHdr(RequestDtlSearch search);

    ApiRequestResponse findLineById(Long lineId);
}

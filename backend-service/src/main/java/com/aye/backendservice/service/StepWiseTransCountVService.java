package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;

import java.util.List;

public interface StepWiseTransCountVService {
    ApiRequestResponse getCountByDetailId(List<Long> detailId);
}

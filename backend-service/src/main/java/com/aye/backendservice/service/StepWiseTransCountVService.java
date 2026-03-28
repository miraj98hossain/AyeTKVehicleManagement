package com.aye.backendservice.service;

import com.aye.dtoLib.dto.response.ApiRequestResponse;

import java.util.List;

public interface StepWiseTransCountVService {
    ApiRequestResponse getCountByDetailId(List<Long> detailId);
}

package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.UserAccessRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface UserAccessBService {
    ApiRequestResponse getAllTemplet();

    ApiRequestResponse findByUserId(Integer userId);

    ApiRequestResponse saveDtlLine(UserAccessRequest userAccessRequest);
}

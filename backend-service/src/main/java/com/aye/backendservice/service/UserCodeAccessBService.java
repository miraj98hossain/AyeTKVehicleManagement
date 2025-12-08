package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.UserCodeAccessRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface UserCodeAccessBService {
    ApiRequestResponse save(UserCodeAccessRequest userCodeAccess);

    ApiRequestResponse findById(Long userCodeAccessId);

    ApiRequestResponse findAllByUser(Integer userId);
}

package com.aye.backendservice.service;

import com.aye.dtoLib.dto.request.UserCodeAccessRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;

public interface UserCodeAccessBService {
    ApiRequestResponse save(UserCodeAccessRequest userCodeAccess, String currentUser);

    ApiRequestResponse findById(Long userCodeAccessId);

    ApiRequestResponse findAllByUser(Integer userId);

    ApiRequestResponse findAllByUser(String userName);
}

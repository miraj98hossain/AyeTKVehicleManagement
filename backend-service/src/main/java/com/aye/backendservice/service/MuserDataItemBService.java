package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface MuserDataItemBService {
    ApiRequestResponse findByOrgId(Long orgId);
}

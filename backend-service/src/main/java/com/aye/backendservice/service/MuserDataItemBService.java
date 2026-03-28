package com.aye.backendservice.service;

import com.aye.dtoLib.dto.response.ApiRequestResponse;

public interface MuserDataItemBService {
    ApiRequestResponse findByOrgId(Long orgId);
}

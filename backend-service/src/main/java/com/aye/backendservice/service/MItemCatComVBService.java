package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface MItemCatComVBService {
    ApiRequestResponse getAllItemCatComb();

    ApiRequestResponse filterItemCatComb(Long orgId, String searchWords);
}

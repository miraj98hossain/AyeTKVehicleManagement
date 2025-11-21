package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface MuserDataCustBService {
    ApiRequestResponse getAllCustomerByOrg(Long orgId);
}

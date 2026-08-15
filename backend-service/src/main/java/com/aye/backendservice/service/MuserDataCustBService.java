package com.aye.backendservice.service;

import com.aye.dto.response.ApiRequestResponse;

public interface MuserDataCustBService {
    ApiRequestResponse getAllCustomerByOrg(Long orgId);
}

package com.aye.backendservice.service;

import com.aye.dtoLib.dto.response.ApiRequestResponse;

public interface MuserDataCustBService {
    ApiRequestResponse getAllCustomerByOrg(Long orgId);
}

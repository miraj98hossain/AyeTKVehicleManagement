package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface EmpResourceBService {
    ApiRequestResponse findEmpResourceByOrgId(Long orgId);
}

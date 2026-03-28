package com.aye.backendservice.service;

import com.aye.dtoLib.dto.response.ApiRequestResponse;

public interface EmpResourceBService {
    ApiRequestResponse findEmpResourceByOrgId(Long orgId);
}

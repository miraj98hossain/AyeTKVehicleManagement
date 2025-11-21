package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.OrgHierarchyRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface OrgHierarchyBService {
    ApiRequestResponse findById(Long orgId);

    ApiRequestResponse findOrgPrntById(Long orgId);

    ApiRequestResponse saveOrg(OrgHierarchyRequest orgHierarchyResponse);
}

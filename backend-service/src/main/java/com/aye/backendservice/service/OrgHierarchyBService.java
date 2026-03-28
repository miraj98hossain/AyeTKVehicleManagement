package com.aye.backendservice.service;

import com.aye.dtoLib.dto.request.OrgHierarchyRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;

public interface OrgHierarchyBService {
    ApiRequestResponse findById(Long orgId);

    ApiRequestResponse findOrgPrntById(Long orgId);

    ApiRequestResponse saveOrg(OrgHierarchyRequest orgHierarchyResponse);

    ApiRequestResponse getAllOrgHierachy();

    ApiRequestResponse findByType(String OrgType);
}

package com.aye.backendservice.service;

import com.aye.dtoLib.dto.request.OrgHierarchyRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;

public interface OrgHierarchyBService {
    ApiRequestResponse findById(Long orgId);

    ApiRequestResponse findOrgPrntById(Long orgId);

    ApiRequestResponse findAllByParentOrgId(Long parentOrgId);

    ApiRequestResponse saveOrg(OrgHierarchyRequest orgHierarchyResponse, String userName);

    ApiRequestResponse getAllOrgHierachy();

    ApiRequestResponse findByType(String OrgType);
}

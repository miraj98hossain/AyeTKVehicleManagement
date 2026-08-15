package com.aye.backendservice.service;


import com.aye.dto.request.OrgHierarchyRequest;
import com.aye.dto.response.ApiRequestResponse;

public interface OrgHierarchyBService {
    ApiRequestResponse findById(Long orgId);

    ApiRequestResponse findOrgPrntById(Long orgId);

    ApiRequestResponse findAllByParentOrgId(Long parentOrgId);

    ApiRequestResponse saveOrg(OrgHierarchyRequest orgHierarchyResponse, String userName);

    ApiRequestResponse getAllOrgHierachy();

    ApiRequestResponse findByType(String OrgType);
}

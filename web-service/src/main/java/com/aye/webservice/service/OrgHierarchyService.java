package com.aye.webservice.service;


import com.aye.commonlib.dto.request.OrgHierarchyRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.OrgHierarchyFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgHierarchyService {

    @Autowired
    OrgHierarchyFeignClient controller;

    public ApiRequestResponse findById(Long orgId) {
        return this.controller.findById(orgId).getBody();
    }

    public ApiRequestResponse findOrgPrntById(Long orgId) {
        return this.controller.findOrgPrntById(orgId).getBody();
    }

    public ApiRequestResponse saveOrg(OrgHierarchyRequest orgHierarchyResponse) {
        return this.controller.saveOrg(orgHierarchyResponse).getBody();
    }

    public ApiRequestResponse getAllOrgHierachy() {
        return this.controller.getAllOrgHierachy().getBody();
    }

    public ApiRequestResponse findByType(String orgType) {
        return this.controller.findByType(orgType).getBody();
    }
}

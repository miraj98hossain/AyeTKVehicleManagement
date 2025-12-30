package com.aye.backendservice.service;

import com.aye.RestfulServer.model.common.OrgType;
import com.aye.RestfulServer.model.om.OrgHierarchy;
import com.aye.RestfulServer.service.OrgHierarchyService;
import com.aye.backendservice.mapper.OrgHierarchyMapper;
import com.aye.commonlib.dto.request.OrgHierarchyRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.OrgHierarchyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgHierarchyBServiceImpl implements OrgHierarchyBService {
    @Autowired
    OrgHierarchyService orgHierarchyService;
    @Autowired
    private OrgHierarchyMapper orgHierarchyMapper;

    @Override
    public ApiRequestResponse findById(Long orgId) {
        OrgHierarchy orgHierarchy = orgHierarchyService.findById(orgId);
        OrgHierarchyResponse orgHierarchyResponse = orgHierarchyMapper.toResponseDto(orgHierarchy);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "org",
                OrgHierarchyResponse.class.getName(), orgHierarchyResponse
        );
    }

    @Override
    public ApiRequestResponse findOrgPrntById(Long orgId) {
        OrgHierarchy orgHierarchy = orgHierarchyService.findById(orgId);
        OrgHierarchy pOrgHierarchy = orgHierarchyService.findById(orgHierarchy.getOrgHierarchy().getId());
        OrgHierarchyResponse orgHierarchyResponse = orgHierarchyMapper.toResponseDto(pOrgHierarchy);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "parentOrg",
                OrgHierarchyResponse.class.getName(), orgHierarchyResponse
        );
    }

    @Override
    public ApiRequestResponse saveOrg(OrgHierarchyRequest orgHierarchyRequest) {
        OrgHierarchy pOrgHierarchy = orgHierarchyService.findById(orgHierarchyRequest.getPOrgHierarchy());
        OrgHierarchy orgHierarchy = orgHierarchyMapper.dtoToEntity(orgHierarchyRequest);
        orgHierarchy.setOrgHierarchy(pOrgHierarchy);
        orgHierarchyService.saveOrg(orgHierarchy);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                null, null,
                null, null
        );
    }

    @Override
    public ApiRequestResponse getAllOrgHierachy() {
        List<OrgHierarchyResponse> orgHResponseList = this.orgHierarchyService.findall()
                .stream().map(this.orgHierarchyMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "orgHierarchyList",
                OrgHierarchyResponse.class.getName(), orgHResponseList
        );
    }

    @Override
    public ApiRequestResponse findByType(String orgType) {

        List<OrgHierarchyResponse> orgHResponseList = this.orgHierarchyService.findByType(OrgType.valueOf(orgType))
                .stream().map(this.orgHierarchyMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "orgHierarchyList",
                OrgHierarchyResponse.class.getName(), orgHResponseList
        );
    }
}

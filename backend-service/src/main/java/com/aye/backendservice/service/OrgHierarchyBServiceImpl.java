package com.aye.backendservice.service;


import com.aye.backendservice.service.orgManagement.OrgHierarchyService;
import com.aye.backendservice.service.user.MuserService;
import com.aye.dto.request.OrgHierarchyRequest;
import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.ApiRequestResponseDetail;
import com.aye.dto.response.userOrg.OrgHierarchyResponse;
import com.aye.entity.CommonColumn;
import com.aye.entity.OrgHierarchy;
import com.aye.entity.user.Muser;
import com.aye.enums.OrgType;
import com.aye.mapper.userOrg.OrgHierarchyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgHierarchyBServiceImpl implements OrgHierarchyBService {
    @Autowired
    private OrgHierarchyService orgHierarchyService;
    @Autowired
    private OrgHierarchyMapper orgHierarchyMapper;
    @Autowired
    private MuserService userService;

    @Override
    public ApiRequestResponse findById(Long orgId) {
        OrgHierarchy orgHierarchy = orgHierarchyService.findById(orgId);
        OrgHierarchyResponse orgHierarchyResponse = orgHierarchyMapper.toResponseDto(orgHierarchy);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "orgHierarchy",
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
    public ApiRequestResponse findAllByParentOrgId(Long parentOrgId) {
        OrgHierarchy orgHierarchy = orgHierarchyService.findById(parentOrgId);
        List<OrgHierarchy> orgHierarchyList = orgHierarchyService.findByHierarchy(orgHierarchy);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "parentOrg",
                OrgHierarchyResponse.class.getName(), orgHierarchyMapper.entityListToResponseDtoList(orgHierarchyList)
        );
    }

    @Override
    public ApiRequestResponse saveOrg(OrgHierarchyRequest orgHierarchyRequest, String userName) {
        Muser dBUser = userService.findByUserName(userName);
        OrgHierarchy orgHierarchy;

        if (orgHierarchyRequest.getId() == null) {
            orgHierarchy = orgHierarchyMapper.dtoToEntity(orgHierarchyRequest);
            CommonColumn commonColumn = new CommonColumn();
            commonColumn.setCreatedBy(dBUser);
            orgHierarchy.setColumn(commonColumn);
        } else {
            orgHierarchy = orgHierarchyService.findById(orgHierarchyRequest.getId());
            orgHierarchyMapper.dtoToEntity(orgHierarchyRequest, orgHierarchy);
            CommonColumn commonColumn = orgHierarchy.getColumn();
            commonColumn.setLastUpdateBy(dBUser.getId());
            orgHierarchy.setColumn(commonColumn);
        }
        if (orgHierarchyRequest.getParentId() != null) {
            OrgHierarchy pOrgHierarchy = orgHierarchyService.findById(orgHierarchyRequest.getParentId());
            orgHierarchy.setOrgHierarchy(pOrgHierarchy);
        } else {
            orgHierarchy.setOrgHierarchy(null);
        }

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

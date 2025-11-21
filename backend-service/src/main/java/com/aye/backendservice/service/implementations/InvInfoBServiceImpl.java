package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.model.om.InventoryInformations;
import com.aye.RestfulServer.model.om.OrgHierarchy;
import com.aye.RestfulServer.service.InvInformationsService;
import com.aye.RestfulServer.service.OrgHierarchyService;
import com.aye.backendservice.mapper.InventoryInformationMapper;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.InvInfoBService;
import com.aye.commonlib.dto.request.InventoryInformationRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.InventoryInformationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvInfoBServiceImpl implements InvInfoBService {
    @Autowired
    private InvInformationsService invInformationsService;
    @Autowired
    private InventoryInformationMapper invMapper;
    @Autowired
    private OrgHierarchyService orgHierarchyService;

    @Override
    public ApiRequestResponse findOne(Long inventoryId) {
        InventoryInformations inventory = this.invInformationsService.findOne(inventoryId);
        InventoryInformationResponse invResponse = invMapper.toResponseDto(inventory);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "inventoryInfo",
                InventoryInformationResponse.class.getName(), invResponse
        );
    }

    @Override
    public ApiRequestResponse save(InventoryInformationRequest invRequest) {


        OrgHierarchy orgHierarchy = this.orgHierarchyService.findById(invRequest.getOrgHierarchyId());
        InventoryInformations inventory = this.invMapper.dtoToEntity(invRequest);
        inventory.setOrgHierarchy(orgHierarchy);

        if (!(inventory.getIsItemMaster())) {
            InventoryInformations itemOrg = this.invInformationsService.findOne(invRequest.getItemOrg());
            inventory.setItemOrg(itemOrg);
        } else {
            inventory.setItemOrg(null);
        }
        this.invInformationsService.Save(inventory);
        var invResponse = invMapper.toResponseDto(inventory);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "inventoryInfo",
                InventoryInformationResponse.class.getName(), invResponse
        );
    }

    @Override
    public ApiRequestResponse findByOu(Long orgId) {
        List<InventoryInformationResponse> inventoryInformationsList = this.invInformationsService.findByOu(orgId)
                .stream().map(invMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "inventoryInfos",
                InventoryInformationResponse.class.getName(), inventoryInformationsList
        );
    }
}


package com.aye.backendservice.service;

import com.aye.RestfulServer.model.om.InventoryInformations;
import com.aye.RestfulServer.service.InvInformationsService;
import com.aye.backendservice.mapper.InventoryInformationMapper;
import com.aye.commonlib.dto.request.InventoryInformationRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.InventoryInformationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvInformationBServiceImpl implements InvInformationBService {
    @Autowired
    InvInformationsService invService;
    @Autowired
    InventoryInformationMapper inventoryMapper;

    @Override
    public ApiRequestResponse findAll() {
        List<InventoryInformationResponse> list = this.invService.findAll().stream()
                .map(inventoryMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "invInfoList",
                InventoryInformationResponse.class.getName(), list
        );
    }

    @Override
    public ApiRequestResponse findMasterItemInf(Boolean isItemMaster) {
        InventoryInformationResponse obj = this.inventoryMapper
                .toResponseDto(invService.findMasterItemInf(isItemMaster));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "invInfo",
                InventoryInformationResponse.class.getName(), obj
        );
    }

    @Override
    public ApiRequestResponse findMasterItemInfBg(Boolean isItemMaster, Long orgHierarchyId) {
        InventoryInformationResponse obj = this.inventoryMapper.toResponseDto(invService.findMasterItemInfBg(isItemMaster, orgHierarchyId));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "invInfo",
                InventoryInformationResponse.class.getName(), obj
        );
    }

    @Override
    public ApiRequestResponse findOne(Long id) {
        InventoryInformationResponse obj = this.inventoryMapper.toResponseDto(invService.findOne(id));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "invInfo",
                InventoryInformationResponse.class.getName(), obj
        );
    }

    @Override
    public ApiRequestResponse findByOu(Long orgHierarchyId) {
        List<InventoryInformationResponse> list = this.invService.findByOu(orgHierarchyId).stream()
                .map(inventoryMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "invInfoList",
                InventoryInformationResponse.class.getName(), list
        );
    }

    @Override
    public ApiRequestResponse findByOuNotItemOrg(Long orgHierarchyId) {
        List<InventoryInformationResponse> list = this.invService.findByOuNotItemOrg(orgHierarchyId).stream()
                .map(inventoryMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "invInfoList",
                InventoryInformationResponse.class.getName(), list
        );
    }

    @Override
    public ApiRequestResponse save(InventoryInformationRequest inventoryInformationRequest) {
        InventoryInformations inventoryInformations = this.inventoryMapper.dtoToEntity(inventoryInformationRequest);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), null,
                null, null,
                null, null
        );
    }

    @Override
    public ApiRequestResponse delete(Long invId) {
        this.invService.delete(invId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), null,
                null, null,
                null, null
        );
    }
}

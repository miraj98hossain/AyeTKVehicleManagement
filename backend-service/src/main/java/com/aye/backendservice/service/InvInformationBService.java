package com.aye.backendservice.service;

import com.aye.dtoLib.dto.request.InventoryInformationRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;

public interface InvInformationBService {
    ApiRequestResponse findAll();

    ApiRequestResponse findMasterItemInf(Boolean isItemMaster);

    ApiRequestResponse findMasterItemInfBg(Boolean isItemMaster, Long orgHierarchyId);

    ApiRequestResponse findOne(Long id);

    ApiRequestResponse findByOu(Long orgHierarchyId);

    ApiRequestResponse findByOuNotItemOrg(Long orgHierarchyId);

    ApiRequestResponse save(InventoryInformationRequest inventoryInformationRequest);

    ApiRequestResponse delete(Long invId);
}

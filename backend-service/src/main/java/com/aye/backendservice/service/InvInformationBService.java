package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.InventoryInformationRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;

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

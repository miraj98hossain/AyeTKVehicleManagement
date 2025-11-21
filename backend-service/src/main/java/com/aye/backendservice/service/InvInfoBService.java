package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.InventoryInformationRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface InvInfoBService {
    ApiRequestResponse findOne(Long inventoryId);

    ApiRequestResponse save(InventoryInformationRequest invRequest);

    ApiRequestResponse findByOu(Long orgId);
}

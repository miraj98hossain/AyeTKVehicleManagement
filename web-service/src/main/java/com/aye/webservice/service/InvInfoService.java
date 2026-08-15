package com.aye.webservice.service;


import com.aye.dto.request.InventoryInformationRequest;
import com.aye.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.InvInfoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InvInfoService {
    @Autowired
    InvInfoFeignClient invInfoFeignClient;

    public ApiRequestResponse findAll() {
        return invInfoFeignClient.findAll().getBody();
    }

    public ApiRequestResponse findMasterItemInf(Boolean isItemMaster) {
        return invInfoFeignClient.findMasterItemInf(isItemMaster).getBody();
    }

    public ApiRequestResponse findMasterItemInfBg(Boolean isItemMaster,
                                                  Long orgHierarchyId) {
        return invInfoFeignClient.findMasterItemInfBg(isItemMaster, orgHierarchyId).getBody();
    }

    public ApiRequestResponse findOne(Long id) {
        return invInfoFeignClient.findOne(id).getBody();
    }


    public ApiRequestResponse findByOu(Long orgHierarchyId) {
        return invInfoFeignClient.findByOu(orgHierarchyId).getBody();
    }


    public ApiRequestResponse findByOuNotItemOrg(Long orgHierarchyId) {
        return invInfoFeignClient.findByOuNotItemOrg(orgHierarchyId).getBody();
    }


    public ApiRequestResponse save(
            InventoryInformationRequest inventoryInformationRequest, String userName) {
        return invInfoFeignClient.save(inventoryInformationRequest, userName).getBody();
    }


    public ApiRequestResponse delete(Long invId) {
        return invInfoFeignClient.delete(invId).getBody();
    }
}

package com.aye.webservice.feignclient;


import com.aye.commonlib.dto.request.InventoryInformationRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "InvInfoFeignClient",
        url = "${backend.service.url}/api/inv-info")
public interface InvInfoFeignClient {

    @GetMapping("/findAll")
    ResponseEntity<ApiRequestResponse> findAll();

    @GetMapping("/findMasterItemInf")
    ResponseEntity<ApiRequestResponse> findMasterItemInf(@RequestParam Boolean isItemMaster);

    @GetMapping("/findMasterItemInfBg")
    ResponseEntity<ApiRequestResponse> findMasterItemInfBg(@RequestParam Boolean isItemMaster,
                                                           @RequestParam Long orgHierarchyId);

    @GetMapping("/findOne/{id}")
    ResponseEntity<ApiRequestResponse> findOne(@PathVariable("id") Long id);

    @GetMapping("/findByOu/{id}")
    ResponseEntity<ApiRequestResponse> findByOu(@PathVariable("id") Long orgHierarchyId);

    @GetMapping("/findByOuNotItemOrg/{id}")
    ResponseEntity<ApiRequestResponse> findByOuNotItemOrg(@PathVariable("id") Long orgHierarchyId);

    @PostMapping("/save")
    ResponseEntity<ApiRequestResponse> save(@Valid @RequestBody
                                            InventoryInformationRequest inventoryInformationRequest);

    @DeleteMapping
    ResponseEntity<ApiRequestResponse> delete(Long invId);
}

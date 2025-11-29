package com.aye.backendservice.controller;

import com.aye.backendservice.service.InvInformationBService;
import com.aye.commonlib.dto.request.InventoryInformationRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inv-info")
public class InventoryInformationController {

    @Autowired
    InvInformationBService invInformationBService;

    @GetMapping("/findAll")
    public ResponseEntity<ApiRequestResponse> findAll() {
        return ResponseEntity.ok(invInformationBService.findAll());
    }

    @GetMapping("/findMasterItemInf")
    public ResponseEntity<ApiRequestResponse> findMasterItemInf(@RequestParam Boolean isItemMaster) {
        return ResponseEntity.ok(invInformationBService.findMasterItemInf(isItemMaster));
    }

    @GetMapping("/findMasterItemInfBg")
    public ResponseEntity<ApiRequestResponse> findMasterItemInfBg(@RequestParam Boolean isItemMaster, @RequestParam Long orgHierarchyId) {
        return ResponseEntity.ok(invInformationBService.findMasterItemInfBg(isItemMaster, orgHierarchyId));
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<ApiRequestResponse> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(invInformationBService.findOne(id));
    }

    @GetMapping("/findByOu/{id}")
    public ResponseEntity<ApiRequestResponse> findByOu(@PathVariable("id") Long orgHierarchyId) {
        return ResponseEntity.ok(invInformationBService.findByOu(orgHierarchyId));
    }

    @GetMapping("/findByOuNotItemOrg/{id}")
    public ResponseEntity<ApiRequestResponse> findByOuNotItemOrg(@PathVariable("id") Long orgHierarchyId) {
        return ResponseEntity.ok(invInformationBService.findByOuNotItemOrg(orgHierarchyId));
    }

    @PostMapping("/save")
    public ResponseEntity<ApiRequestResponse> save(@Valid @RequestBody InventoryInformationRequest inventoryInformationRequest) {
        return ResponseEntity.ok().body(this.invInformationBService.save(inventoryInformationRequest));
    }

    @DeleteMapping
    public ResponseEntity<ApiRequestResponse> delete(Long invId) {
        return ResponseEntity.ok().body(this.invInformationBService.delete(invId));
    }
}

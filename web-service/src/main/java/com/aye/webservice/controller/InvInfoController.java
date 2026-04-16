package com.aye.webservice.controller;


import com.aye.dtoLib.dto.request.InventoryInformationRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.InvInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/inv-info")
public class InvInfoController {

    @Autowired
    private InvInfoService invInfoService;

    @GetMapping("/findAll")
    public ResponseEntity<ApiRequestResponse> findAll() {
        return ResponseEntity.ok(this.invInfoService.findAll());
    }

    @GetMapping("/findMasterItemInf")
    public ResponseEntity<ApiRequestResponse> findMasterItemInf(@RequestParam Boolean isItemMaster) {
        return ResponseEntity.ok(this.invInfoService.findMasterItemInf(isItemMaster));
    }

    @GetMapping("/findMasterItemInfBg")
    public ResponseEntity<ApiRequestResponse> findMasterItemInfBg(@RequestParam Boolean isItemMaster, @RequestParam Long orgHierarchyId) {
        return ResponseEntity.ok(this.invInfoService.findMasterItemInfBg(isItemMaster, orgHierarchyId));
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<ApiRequestResponse> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.invInfoService.findOne(id));
    }

    @GetMapping("/findByOu/{id}")
    public ResponseEntity<ApiRequestResponse> findByOu(@PathVariable("id") Long orgHierarchyId) {
        return ResponseEntity.ok(this.invInfoService.findByOu(orgHierarchyId));
    }

    @GetMapping("/findByOuNotItemOrg/{id}")
    public ResponseEntity<ApiRequestResponse> findByOuNotItemOrg(@PathVariable("id") Long orgHierarchyId) {
        return ResponseEntity.ok(this.invInfoService.findByOuNotItemOrg(orgHierarchyId));
    }

    @PostMapping("/save")
    public ResponseEntity<ApiRequestResponse> save(@Valid @RequestBody InventoryInformationRequest inventoryInformationRequest) {
        return ResponseEntity.ok().body(this.invInfoService.save(inventoryInformationRequest));
    }

    @DeleteMapping
    public ResponseEntity<ApiRequestResponse> delete(Long invId) {
        return ResponseEntity.ok().body(this.invInfoService.delete(invId));
    }
}

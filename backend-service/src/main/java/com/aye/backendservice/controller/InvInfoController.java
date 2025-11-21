package com.aye.backendservice.controller;


import com.aye.backendservice.service.InvInfoBService;
import com.aye.commonlib.dto.request.InventoryInformationRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/invInfo")
public class InvInfoController {

    @Autowired
    private InvInfoBService invInfoBService;

    @GetMapping("/findOne/{invId}")
    ResponseEntity<ApiRequestResponse> findOne(@PathVariable("invId") Long invId) {
        return ResponseEntity.ok(invInfoBService.findOne(invId));
    }

    @PostMapping("/invInfoSave")
    ResponseEntity<ApiRequestResponse> save(InventoryInformationRequest invRequest) {
        return ResponseEntity.ok(invInfoBService.save(invRequest));
    }

    @GetMapping("/findByOu/{orgId}")
    ResponseEntity<ApiRequestResponse> findByOu(@PathVariable("orgId") Long orgId) {
        return ResponseEntity.ok(invInfoBService.findByOu(orgId));
    }
}

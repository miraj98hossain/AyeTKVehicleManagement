package com.aye.webservice.controller;


import com.aye.commonlib.dto.request.InventoryInformationRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.InvInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/invInfo")
public class InvInfoController {

    @Autowired
    private InvInfoService invInfoService;

    @GetMapping("/findOne/{invId}")
    ResponseEntity<ApiRequestResponse> findOne(@PathVariable("invId") Long invId) {
        return ResponseEntity.ok(invInfoService.findOne(invId));
    }

    @PostMapping("/invInfoSave")
    ResponseEntity<ApiRequestResponse> save(InventoryInformationRequest invRequest) {
        return ResponseEntity.ok(invInfoService.save(invRequest));
    }

    @GetMapping("/findByOu/{orgId}")
    ResponseEntity<ApiRequestResponse> findByOu(@PathVariable("orgId") Long orgId) {
        return ResponseEntity.ok(invInfoService.findByOu(orgId));
    }
}

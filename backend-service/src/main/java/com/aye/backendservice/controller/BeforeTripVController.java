package com.aye.backendservice.controller;

import com.aye.backendservice.service.BeforeTripVBService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 12:52
 * @project: AyeTKVehicleManagement
 */
@RestController
@RequestMapping("/api/before-trip-V")
public class BeforeTripVController {
    @Autowired
    private BeforeTripVBService beforeTripVBService;

    @GetMapping("/getDeliveryNumbers")
    public ResponseEntity<ApiRequestResponse> getDeliveryNumbers(@RequestParam Long orgId, @RequestParam Long invOrgId) {
        return ResponseEntity.ok().body(beforeTripVBService.getDeliveryNumbers(orgId, invOrgId));
    }
}

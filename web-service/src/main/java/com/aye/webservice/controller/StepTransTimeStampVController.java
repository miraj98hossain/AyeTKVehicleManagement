package com.aye.webservice.controller;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.StepTransTimeStampVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Miraj
 * @date: 01/01/2026
 * @time: 14:23
 * @project: AyeTKVehicleManagement
 */
@RestController
@RequestMapping("/api/strans-time-stamp")
public class StepTransTimeStampVController {
    @Autowired
    private StepTransTimeStampVService stepTransTimeStampVService;

    @GetMapping("/getTimeStampByDetailsId/{stepSetupDetailsId}")
    public ResponseEntity<ApiRequestResponse> getTimeStampByDetailsId(@PathVariable Long stepSetupDetailsId) {
        return ResponseEntity.ok(stepTransTimeStampVService.getTimeStampByDetailsId(stepSetupDetailsId));
    }
}

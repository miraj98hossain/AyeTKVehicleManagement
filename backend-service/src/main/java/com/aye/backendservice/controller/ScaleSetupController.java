package com.aye.backendservice.controller;

import com.aye.backendservice.service.ScaleSetupService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Miraj
 * @date: 13/01/2026
 * @time: 15:52
 */
@RestController
@RequestMapping("/api/scale-setup")
public class ScaleSetupController {
    @Autowired
    private ScaleSetupService scaleSetupService;

    @GetMapping
    public ResponseEntity<ApiRequestResponse> findAllScaleSetup() {
        return ResponseEntity.ok().body(this.scaleSetupService.findAllScaleSetup());
    }

    @GetMapping("/filterScaleSetup")
    public ResponseEntity<ApiRequestResponse> filterScaleSetup(@RequestParam Long orgId) {
        return ResponseEntity.ok().body(this.scaleSetupService.filterScaleSetup(orgId));
    }
}

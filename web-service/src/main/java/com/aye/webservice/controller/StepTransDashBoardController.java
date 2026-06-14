package com.aye.webservice.controller;

import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.StepTransDashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/step-trans-dashboard")
public class StepTransDashBoardController {
    @Autowired
    private StepTransDashBoardService service;

    @GetMapping("/getStepWiseVehicleSummary")
    public ResponseEntity<ApiRequestResponse> getStepWiseVehicleSummary() {
        return ResponseEntity.ok().body(service.getStepWiseVehicleSummary());
    }

    @GetMapping("/getStepTransactionVolume")
    public ResponseEntity<ApiRequestResponse> getStepTransactionVolume() {
        return ResponseEntity.ok().body(service.getStepTransactionVolume());
    }

    @GetMapping("/getStepTransTotalTime")
    public ResponseEntity<ApiRequestResponse> getStepTransTotalTime() {
        return ResponseEntity.ok().body(service.getStepTransTotalTime());
    }

    @GetMapping("/getStrStepWiseTotalTimeByTransId/{stepTransId}")
    public ResponseEntity<ApiRequestResponse> getStrStepWiseTotalTimeByTransId(@PathVariable Long stepTransId) {
        return ResponseEntity.ok().body(service.getStrStepWiseTotalTimeByTransId(stepTransId));
    }
}
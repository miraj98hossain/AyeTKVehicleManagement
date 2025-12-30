package com.aye.backendservice.controller;

import com.aye.RestfulServer.model.om.BeforeTripWDsV;
import com.aye.RestfulServer.service.BeforeTripWDsVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 10:48
 * @project: AyeTKVehicleManagement
 */
@RestController
@RequestMapping("/api/BeforeTripWDsV")
public class BeforeTripWDsVController {
    @Autowired
    private BeforeTripWDsVService beforeTripWDsVService;

    @GetMapping()
    public ResponseEntity<List<BeforeTripWDsV>> findAll() {
        return ResponseEntity.ok(beforeTripWDsVService.findAll());
    }
}

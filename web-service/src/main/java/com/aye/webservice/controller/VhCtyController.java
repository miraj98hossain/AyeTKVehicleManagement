package com.aye.webservice.controller;


import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.VhCityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/vh-cty")
public class VhCtyController {
    @Autowired
    private VhCityService vhCityService;


    @GetMapping
    public ResponseEntity<ApiRequestResponse> getAllVehicleCity() {
        var list = this.vhCityService.getAllVehicleCity();
        if (list == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }
}

package com.mhdev.backendservice.controller;

import com.mhdev.backendservice.service.VehicleCityService;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/vh-cty")
public class VehicleCityController {
    @Autowired
    private VehicleCityService vehicleCityService;

    @GetMapping
    public ResponseEntity<ApiRequestResponse> getAllVehicleCity() {
        var list = this.vehicleCityService.getAllVehicleCity();
        if (list == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

}

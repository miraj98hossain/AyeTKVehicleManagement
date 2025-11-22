package com.aye.webservice.controller;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.MuserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/muser-data")
public class MuserDataController {

    @Autowired
    private MuserDataService muserDataService;

    @GetMapping("/findUserItemByOrgId/{orgId}")
    ResponseEntity<ApiRequestResponse> findByOrgId(@PathVariable("orgId") Long orgId) {
        return ResponseEntity.ok(muserDataService.findByOrgId(orgId));
    }

    @GetMapping("/findAllCustomerByOrg/{orgId}")
    ResponseEntity<ApiRequestResponse> getAllCustomerByOrg(@PathVariable("orgId") Long orgId) {
        return ResponseEntity.ok(muserDataService.getAllCustomerByOrg(orgId));
    }
}

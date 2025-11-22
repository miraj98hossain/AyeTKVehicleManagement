package com.aye.backendservice.controller;

import com.aye.backendservice.service.MuserDataCustBService;
import com.aye.backendservice.service.MuserDataItemBService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
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
    MuserDataItemBService muserDataItemService;
    @Autowired
    MuserDataCustBService muserDataCustService;

    @GetMapping("/findUserItemByOrgId/{orgId}")
    ResponseEntity<ApiRequestResponse> findByOrgId(@PathVariable("orgId") Long orgId) {
        return ResponseEntity.ok(muserDataItemService.findByOrgId(orgId));
    }

    @GetMapping("/findAllCustomerByOrg/{orgId}")
    ResponseEntity<ApiRequestResponse> getAllCustomerByOrg(@PathVariable("orgId") Long orgId) {
        return ResponseEntity.ok(muserDataCustService.getAllCustomerByOrg(orgId));
    }
}

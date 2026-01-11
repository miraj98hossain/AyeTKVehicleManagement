package com.aye.backendservice.controller;

import com.aye.backendservice.service.XxtkgTripDelvDtlVBService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Miraj
 * @date: 08/01/2026
 * @time: 12:33
 */
@RestController
@RequestMapping("/api/trip-dtls")
public class XxtkgTripDelvDtlVBController {

    @Autowired
    private XxtkgTripDelvDtlVBService service;

    @GetMapping("/filterChallanNumber")
    public ResponseEntity<ApiRequestResponse> filterChallanNumber(@RequestParam Long orgId,
                                                                  @RequestParam Long invOrgId,
                                                                  @RequestParam String searchWords) {
        return ResponseEntity.ok().body(service.filterChallanNumber(orgId, invOrgId, searchWords));
    }
}

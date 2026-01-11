package com.aye.webservice.controller;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.XxtkgTripDelvDtlVService;
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
    private XxtkgTripDelvDtlVService service;

    @GetMapping("/filterChallanNumber")
    public ResponseEntity<ApiRequestResponse> filterChallanNumber(@RequestParam Long orgId,
                                                                  @RequestParam Long invOrgId,
                                                                  @RequestParam String searchWords) {
        return ResponseEntity.ok().body(service.filterChallanNumber(orgId, invOrgId, searchWords));
    }
}

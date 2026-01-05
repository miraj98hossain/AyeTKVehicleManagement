package com.aye.webservice.controller;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.OrderedCustomerVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 10:21
 */
@RestController
@RequestMapping("/api/orderedCustomerV")
public class OrderedCustomerVController {
    @Autowired
    private OrderedCustomerVService orderedCustomerVService;

    @GetMapping("/filterCustomer")
    public ResponseEntity<ApiRequestResponse> filterCustomer(@RequestParam Long orgId,
                                                             @RequestParam String searchWords) {
        return ResponseEntity.ok(orderedCustomerVService.filterCustomer(orgId, searchWords));
    }
}

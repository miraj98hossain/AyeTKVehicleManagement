package com.aye.webservice.controller;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.OrderedItemVService;
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
@RequestMapping("/api/orderedItemV")
public class OrderedItemVController {
    @Autowired
    private OrderedItemVService orderedItemVService;

    @GetMapping("/filterOrderedItem")
    public ResponseEntity<ApiRequestResponse> filterOrderedItem(@RequestParam Long orgId,
                                                                @RequestParam Long invOrgId,
                                                                @RequestParam String searchWords) {
        return ResponseEntity.ok(orderedItemVService.filterOrderedItem(orgId, invOrgId, searchWords));
    }
}

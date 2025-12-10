package com.aye.webservice.controller;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.MItemCatComVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/m-item-cat-comb")
public class MItemCatComVController {
    @Autowired
    private MItemCatComVService mItemCatComVService;

    @GetMapping("/getAllItemCatComb")
    public ResponseEntity<ApiRequestResponse> getAllItemCatComb() {
        return ResponseEntity.ok().body(this.mItemCatComVService.getAllItemCatComb());
    }

    @GetMapping("/filterItemCatComb/{orgId}")
    public ResponseEntity<ApiRequestResponse> filterItemCatComb(
            @PathVariable("orgId") Long orgId,
            @RequestParam("searchWords") String searchWords) {
        return ResponseEntity.ok().body(this.mItemCatComVService.filterItemCatComb(orgId, searchWords));
    }
}

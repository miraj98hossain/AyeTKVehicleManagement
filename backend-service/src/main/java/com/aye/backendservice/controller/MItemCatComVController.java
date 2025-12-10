package com.aye.backendservice.controller;

import com.aye.backendservice.service.MItemCatComVBService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/m-item-cat-comb")
public class MItemCatComVController {
    @Autowired
    private MItemCatComVBService mItemCatComVBService;

    @GetMapping("/getAllItemCatComb")
    public ResponseEntity<ApiRequestResponse> getAllItemCatComb() {
        return ResponseEntity.ok().body(this.mItemCatComVBService.getAllItemCatComb());
    }

    @GetMapping("/filterItemCatComb/{orgId}")
    public ResponseEntity<ApiRequestResponse> filterItemCatComb(
            @PathVariable("orgId") Long orgId,
            @RequestParam("searchWords") String searchWords) {
        return ResponseEntity.ok().body(this.mItemCatComVBService.filterItemCatComb(orgId, searchWords));
    }
}

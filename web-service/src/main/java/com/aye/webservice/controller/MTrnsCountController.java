package com.aye.webservice.controller;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.MTrnsCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mtrns-count")
public class MTrnsCountController {

    @Autowired
    private MTrnsCountService mtrnsCountService;


    @GetMapping("/findAll")
    public ResponseEntity<ApiRequestResponse> findAll() {
        return ResponseEntity.ok(this.mtrnsCountService.findAll());
    }

}

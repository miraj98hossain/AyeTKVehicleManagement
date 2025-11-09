package com.mhdev.webservice.controller;


import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import com.mhdev.webservice.service.StepTransService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/step-trans")
public class StepTransController {
    @Autowired
    private StepTransService stepTransService;

    @PostMapping("/create")
    public ResponseEntity<ApiRequestResponse> create(
            @RequestParam Long currentUserId,
            @RequestBody StepTransRequest stepTransRequest, UriComponentsBuilder uriBuilder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.stepTransService.create(stepTransRequest, currentUserId));
    }

    @PostMapping("/update-lines")
    public ResponseEntity<ApiRequestResponse> updateLines(
            @RequestParam Long currentUserId,
            @RequestBody StepTransLinesRequest stepTransLinesRequest) {
        return ResponseEntity.ok().body(this.stepTransService.updateLines(stepTransLinesRequest, currentUserId));
    }

    @GetMapping
    public ResponseEntity<ApiRequestResponse> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.stepTransService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.stepTransService.findById(id));
    }

    @GetMapping("/findAllBySetupDtls")
    public ResponseEntity<ApiRequestResponse> findAllBySetupDtls(@RequestParam List<Long> setupDetailIds, Pageable pageable) {
        return ResponseEntity.ok().body(this.stepTransService.findAllBySetupDtls(setupDetailIds, pageable));
    }
}

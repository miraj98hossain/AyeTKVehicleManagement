package com.mhdev.backendservice.controller;


import com.mhdev.backendservice.service.StepTransService;
import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.StepTransLinesResponse;
import com.mhdev.commonlib.dto.response.StepTransResponse;
import com.mhdev.commonlib.dto.validationGroup.StepTransCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepTransLinesUpdateValidation;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/step-trans")
public class StepTransController {
    @Autowired
    private StepTransService stepTransService;

    @PostMapping("/create")
    public ResponseEntity<StepTransResponse> create(@Validated({StepTransCreateValidation.class, Default.class}) @RequestBody StepTransRequest stepTransRequest, UriComponentsBuilder uriBuilder) {
        try {
            StepTransResponse stepTransResponse = this.stepTransService.saveStepTrans(stepTransRequest);
            URI location = uriBuilder
                    .path("/step-trans/{id}")
                    .buildAndExpand(stepTransResponse.getStepTransId())
                    .toUri();
            return ResponseEntity.created(location).body(stepTransResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<StepTransLinesResponse> update(@Validated({StepTransLinesUpdateValidation.class, Default.class}) @RequestBody StepTransLinesRequest stepTransLinesRequest) {
        try {
            StepTransLinesResponse stepTransLinesResponse = this.stepTransService.updateTrans(stepTransLinesRequest);
            return ResponseEntity.ok().body(stepTransLinesResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<StepTransResponse>> getAllStepTrans(Pageable pageable) {
        try {
            Page<StepTransResponse> list = this.stepTransService.getAllStepTrans(pageable);
            if (list == null || list.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StepTransResponse> getStepTrans(@PathVariable("id") Long id) {
        try {
            StepTransResponse stepTransResponse = this.stepTransService.getStepTrans(id);
            return ResponseEntity.ok().body(stepTransResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

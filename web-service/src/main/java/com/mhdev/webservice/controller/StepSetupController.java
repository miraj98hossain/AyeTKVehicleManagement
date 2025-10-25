package com.mhdev.webservice.controller;


import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.StepSetupDetailsResponse;
import com.mhdev.commonlib.dto.response.StepSetupResponse;
import com.mhdev.commonlib.dto.validationGroup.StepSetupCreateValidation;
import com.mhdev.webservice.service.StepSetupService;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/step-setup")
public class StepSetupController {
    @Autowired
    StepSetupService stepSetupService;

    @PostMapping("/save")
    public ResponseEntity<StepSetupResponse> saveStepSetup(@Validated({StepSetupCreateValidation.class}) @RequestBody StepSetupRequest stepSetupRequest, UriComponentsBuilder uriBuilder) {
        try {
            StepSetupResponse savedStepSetup = stepSetupService.saveStepSetup(stepSetupRequest);
            URI location = uriBuilder
                    .path("/step-setup/{id}")
                    .buildAndExpand(savedStepSetup.getStepSetupId())
                    .toUri();
            return ResponseEntity.created(location).body(savedStepSetup);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<StepSetupDetailsResponse>> getStepSetup(@PathVariable("id") Long id) {
        try {
            var list = this.stepSetupService.getStepSetup(id);
            if (list == null || list.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {

            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Page<StepSetupResponse>> getAllStepsSetup(Pageable pageable) {
        try {
            var list = this.stepSetupService.getAllStepsSetup(pageable);
            if (list == null || list.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

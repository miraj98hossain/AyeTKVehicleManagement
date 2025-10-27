package com.mhdev.backendservice.controller;

import com.mhdev.backendservice.service.StepSetupService;
import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.StepSetupDetailsResponse;
import com.mhdev.commonlib.dto.response.StepSetupResponse;
import com.mhdev.commonlib.dto.validationGroup.StepSetupCreateValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
        StepSetupResponse savedStepSetup = stepSetupService.saveStepSetup(stepSetupRequest);
        URI location = uriBuilder
                .path("/step-setup/{id}")
                .buildAndExpand(savedStepSetup.getStepSetupId())
                .toUri();
        return ResponseEntity.created(location).body(savedStepSetup);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<StepSetupDetailsResponse>> getStepSetup(@PathVariable("id") Long id) {
        var list = this.stepSetupService.findByIdRes(id);
        if (list == null || list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping()
    public ResponseEntity<List<StepSetupResponse>> getAllStepsSetup(Pageable pageable) {
        var list = this.stepSetupService.findAllStepSetup();
        if (list == null || list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }
}

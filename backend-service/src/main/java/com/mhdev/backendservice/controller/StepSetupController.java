package com.mhdev.backendservice.controller;

import com.mhdev.backendservice.service.StepSetupService;
import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import com.mhdev.commonlib.dto.validationGroup.StepSetupCreateValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/step-setup")
public class StepSetupController {
    @Autowired
    StepSetupService stepSetupService;

    @PostMapping("/save")
    public ResponseEntity<ApiRequestResponse> saveStepSetup(@Validated({StepSetupCreateValidation.class}) @RequestBody StepSetupRequest stepSetupRequest, UriComponentsBuilder uriBuilder) {

        ApiRequestResponse response = stepSetupService.saveStepSetup(stepSetupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> getStepSetup(@PathVariable("id") Long id) {
        var list = this.stepSetupService.findByIdRes(id);
        if (list == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping()
    public ResponseEntity<ApiRequestResponse> getAllStepsSetup(Pageable pageable) {
        var list = this.stepSetupService.findAllStepSetup(pageable);
        if (list == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/filterStepSetup")
    public ResponseEntity<ApiRequestResponse> filterStepSetup(
            @RequestParam() Long orgId, @RequestParam() Long invOrgId) {
        var list = this.stepSetupService.filterStepSetup(orgId, invOrgId);
        if (list == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }
}

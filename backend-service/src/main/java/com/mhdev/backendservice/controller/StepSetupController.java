package com.mhdev.backendservice.controller;

import com.mhdev.backendservice.service.StepSetupService;
import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.StepSetupDetailsResponse;
import com.mhdev.commonlib.dto.response.StepSetupResponse;
import com.mhdev.commonlib.dto.validationGroup.StepSetupCreateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
//    @PutMapping("/save")
//    public ResponseEntity<StepSetupResponse> updateStepSetup(@Validated({StepSetupUpdateValidation.class})@RequestBody StepSetupRequest stepSetupRequest){
//        try {
//            StepSetupResponse savedStepSetup = stepSetupService.saveStepSetup(stepSetupRequest);
//            return ResponseEntity.ok().body(savedStepSetup);
//        } catch (Exception e) {
//
//            e.printStackTrace();
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<List<StepSetupDetailsResponse>> getStepSetup(@PathVariable("id") Long id) {
        try {
            var list = this.stepSetupService.findByIdRes(id);
            if (list == null || list.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {

            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<StepSetupResponse>> getAllStepsSetup(Pageable pageable) {
        try {
            var list = this.stepSetupService.findAllStepSetup();
            if (list == null || list.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

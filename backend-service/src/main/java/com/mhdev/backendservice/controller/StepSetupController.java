package com.mhdev.backendservice.controller;

import com.mhdev.backendservice.service.StepSetupService;
import com.mhdev.commonlib.dto.request.StepSetupRequest;
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
    public ResponseEntity<Response> saveStepSetup(@Validated({StepSetupCreateValidation.class}) @RequestBody StepSetupRequest stepSetupRequest, UriComponentsBuilder uriBuilder) {

        Response response = stepSetupService.saveStepSetup(stepSetupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        try {
//
//
//
//        } catch (Exception ex) {
//            log.error(ex.getMessage(), ex);
//            Response response = new Response();
//            response.setSuccess(Boolean.FALSE);
//            response.setMessage(ex.getMessage());
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getStepSetup(@PathVariable("id") Long id) {
        var list = this.stepSetupService.findByIdRes(id);
        if (list == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping()
    public ResponseEntity<Response> getAllStepsSetup(Pageable pageable) {
        var list = this.stepSetupService.findAllStepSetup(pageable);
        if (list == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }
}

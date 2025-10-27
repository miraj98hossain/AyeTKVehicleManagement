package com.mhdev.webservice.controller;


import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.validationGroup.StepSetupCreateValidation;
import com.mhdev.webservice.service.StepSetupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/step-setup")
public class StepSetupController {
    @Autowired
    StepSetupService stepSetupService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveStepSetup(@Validated({StepSetupCreateValidation.class}) @RequestBody StepSetupRequest stepSetupRequest) {
        var savedStepSetup = stepSetupService.saveStepSetup(stepSetupRequest);
        return ResponseEntity.status(HttpStatus.OK).body(savedStepSetup);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getStepSetup(@PathVariable("id") Long id) {
        var list = this.stepSetupService.getStepSetup(id);
        if (list == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping()
    public ResponseEntity<Response> getAllStepsSetup(Pageable pageable) {
        var list = this.stepSetupService.getAllStepsSetup(pageable);
        if (list == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }
}

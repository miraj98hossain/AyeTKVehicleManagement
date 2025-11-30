package com.aye.webservice.controller;


import com.aye.commonlib.dto.request.StepSetupRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.validationGroup.StepSetupCreateValidation;
import com.aye.webservice.service.StepSetupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/step-setup")
public class StepSetupController {
    @Autowired
    StepSetupService stepSetupService;

    @PostMapping("/save")
    public ResponseEntity<ApiRequestResponse> saveStepSetup(@Validated({StepSetupCreateValidation.class}) @RequestBody StepSetupRequest stepSetupRequest) {
        var savedStepSetup = stepSetupService.saveStepSetup(stepSetupRequest);
        return ResponseEntity.status(HttpStatus.OK).body(savedStepSetup);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> getStepSetup(@PathVariable("id") Long id) {
        var list = this.stepSetupService.getStepSetup(id);
        if (list == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping()
    public ResponseEntity<ApiRequestResponse> getAllStepsSetup(Pageable pageable) {
        var list = this.stepSetupService.getAllStepsSetup(pageable);
        if (list == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/filterStepSetup")
    public ResponseEntity<ApiRequestResponse> filterStepSetup(@RequestParam Long orgId, @RequestParam Long invOrgId, @RequestParam(required = false) String searchWords) {
        var list = this.stepSetupService.filterStepSetup(orgId, invOrgId, searchWords);
        if (list == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/findSetupByDtlId")
    ResponseEntity<ApiRequestResponse> findSetupByDtlId(@RequestParam Long detailId) {
        var obj = this.stepSetupService.findSetupByDtlId(detailId);
        if (obj == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/findSetupByTempDtlId/{tempDtlId}")
    public ResponseEntity<ApiRequestResponse> findSetupByTempDtlId(@PathVariable("tempDtlId") Integer tempDtlId) {
        var obj = this.stepSetupService.findSetupByTempDtlId(tempDtlId);
        if (obj == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(obj);
    }
}

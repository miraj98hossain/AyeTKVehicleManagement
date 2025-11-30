package com.aye.mobileservice.controller;


import com.aye.commonlib.dto.request.StepSetupRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.validationGroup.StepSetupCreateValidation;
import com.aye.mobileservice.service.StepSetupService;
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
    public ResponseEntity<ApiRequestResponse> saveStepSetup(@Validated({StepSetupCreateValidation.class})
                                                            @RequestBody StepSetupRequest stepSetupRequest) {

        ApiRequestResponse response = stepSetupService.saveStepSetup(stepSetupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> getStepSetup(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.stepSetupService.getStepSetup(id));
    }

    @GetMapping()
    public ResponseEntity<ApiRequestResponse> getAllStepsSetup(Pageable pageable) {
        return ResponseEntity.ok().body(this.stepSetupService.getAllStepsSetup(pageable));
    }

    @GetMapping("/filterStepSetup")
    public ResponseEntity<ApiRequestResponse> filterStepSetup(
            @RequestParam Long orgId, @RequestParam Long invOrgId, @RequestParam(required = false) String searchWords) {
        return ResponseEntity.ok().body(this.stepSetupService.filterStepSetup(orgId, invOrgId, searchWords));
    }

    @GetMapping("/findSetupByDtlId")
    public ResponseEntity<ApiRequestResponse> findSetupByDtlId(
            @RequestParam Long detailId) {
        return ResponseEntity.ok().body(this.stepSetupService.findSetupByDtlId(detailId));
    }

    @GetMapping("/findSetupByTempDtlId/{tempDtlId}")
    public ResponseEntity<ApiRequestResponse> findSetupByTempDtlId(@PathVariable("tempDtlId") Integer tempDtlId) {
        return ResponseEntity.ok().body(this.stepSetupService.findSetupByTempDtlId(tempDtlId));
    }
}

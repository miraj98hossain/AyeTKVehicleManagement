package com.aye.webservice.controller;


import com.aye.commonlib.dto.request.StepSetupDetailsRequest;
import com.aye.commonlib.dto.request.StepSetupRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.StepSetupService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/step-setup")
public class StepSetupController {
    @Autowired
    StepSetupService stepSetupService;

    @PostMapping("/save")
    public ResponseEntity<ApiRequestResponse> saveStepSetup(@Valid @RequestBody StepSetupRequest stepSetupRequest,
                                                            @RequestParam String currentUserName) {
        var savedStepSetup = stepSetupService.saveStepSetup(stepSetupRequest, currentUserName);
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
    public ResponseEntity<ApiRequestResponse> filterStepSetup(@RequestParam Long orgId,
                                                              @RequestParam Long invOrgId,
                                                              @RequestParam(required = false) String searchWords) {
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

    @GetMapping("/getAllDetailsBySetup/{setupId}")
    public ResponseEntity<ApiRequestResponse> getAllDetailsBySetup(@PathVariable("setupId") Long setupId) {
        var obj = this.stepSetupService.findAllSetupDetails(setupId);
        if (obj == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/save-dtl")
    public ResponseEntity<ApiRequestResponse> addOrUpdateDetail(
            @RequestBody StepSetupDetailsRequest stepSetupDetailsRequest,
            @RequestParam String currentUserName) {

        return ResponseEntity.ok().body(stepSetupService.addOrUpdateDetail(stepSetupDetailsRequest, currentUserName));
    }

    @GetMapping("/findStepStpDtlByDtlId/{detailId}")
    public ResponseEntity<ApiRequestResponse> findStepStpDtlByDtlId(@PathVariable("detailId") Long detailId) {
        return ResponseEntity.ok().body(stepSetupService.findStepStpDtlByDtlId(detailId));
    }
}

package com.aye.backendservice.controller;

import com.aye.backendservice.service.StepTransScaleDetailsViewService;
import com.aye.dtoLib.dto.response.StepTransScaleDetailsDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/step-trans-scale-details")
public class StepTransScaleDetailsController {
    @Autowired
    private StepTransScaleDetailsViewService service;

    @PostMapping("/save")
    public ResponseEntity<StepTransScaleDetailsDto> save(@Valid @RequestBody StepTransScaleDetailsDto stepTransScaleDetailsDto, @RequestParam String currentUser) {

        return ResponseEntity.ok().body(this.service.save(stepTransScaleDetailsDto, currentUser));
    }

    @GetMapping("/findById")
    public ResponseEntity<StepTransScaleDetailsDto> findById(@RequestParam Long stepTransId,
                                                             @RequestParam Long stepTransLinesId,
                                                             @RequestParam Long stepSetupDetailsId) {
        return ResponseEntity.ok().body(this.service.findById(stepTransId, stepTransLinesId, stepSetupDetailsId));
    }

}
package com.aye.webservice.controller;

import com.aye.dtoLib.dto.response.StepTransScaleDetailsDto;
import com.aye.webservice.service.StepTransScaleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/step-trans-scale-details")
public class StepTransScaleDetailsController {
    @Autowired
    private StepTransScaleDetailsService service;

    @PostMapping("/save")
    public ResponseEntity<StepTransScaleDetailsDto> save(@RequestBody StepTransScaleDetailsDto stepTransScaleDetailsDto, @RequestParam String currentUser) {

        return ResponseEntity.ok().body(this.service.save(stepTransScaleDetailsDto, currentUser));
    }

    @GetMapping("/findById")
    public ResponseEntity<StepTransScaleDetailsDto> findById(@RequestParam Long stepTransId,
                                                             @RequestParam Long stepTransLinesId,
                                                             @RequestParam Long stepSetupDetailsId) {
        return ResponseEntity.ok().body(this.service.findById(stepTransId, stepTransLinesId, stepSetupDetailsId));
    }

}
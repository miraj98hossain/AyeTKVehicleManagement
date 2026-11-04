package com.aye.backendservice.controller;

import com.aye.backendservice.service.StepTransTripDetailsViewService;
import com.aye.dto.response.StepTransTripDetailsDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/step-trans-trip-details")
public class StepTransTripDetailsController {
    @Autowired
    private StepTransTripDetailsViewService service;

    @PostMapping("/save")
    public ResponseEntity<StepTransTripDetailsDto> save(@Valid @RequestBody StepTransTripDetailsDto stepTransTripDetailsDto, @RequestParam String currentUser) {

        return ResponseEntity.ok().body(this.service.save(stepTransTripDetailsDto, currentUser));
    }

    @GetMapping("/findById")
    public ResponseEntity<StepTransTripDetailsDto> findById(@RequestParam Long stepTransId, @RequestParam Long stepTransLinesId) {
        return ResponseEntity.ok().body(this.service.findById(stepTransId, stepTransLinesId));
    }

}
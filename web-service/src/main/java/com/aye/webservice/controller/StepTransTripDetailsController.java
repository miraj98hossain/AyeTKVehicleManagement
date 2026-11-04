package com.aye.webservice.controller;

import com.aye.dto.response.StepTransTripDetailsDto;
import com.aye.webservice.service.StepTransTripDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/step-trans-trip-details")
public class StepTransTripDetailsController {
    @Autowired
    private StepTransTripDetailsService service;

    @PostMapping("/save")
    public ResponseEntity<StepTransTripDetailsDto> save(@RequestBody StepTransTripDetailsDto stepTransTripDetailsDto, @RequestParam String currentUser) {

        return ResponseEntity.ok().body(this.service.save(stepTransTripDetailsDto, currentUser));
    }

    @GetMapping("/findById")
    public ResponseEntity<StepTransTripDetailsDto> findById(@RequestParam Long stepTransId, @RequestParam Long stepTransLinesId) {
        return ResponseEntity.ok().body(this.service.findById(stepTransId, stepTransLinesId));
    }

}
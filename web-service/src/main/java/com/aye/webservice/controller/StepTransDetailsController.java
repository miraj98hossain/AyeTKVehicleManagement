package com.aye.webservice.controller;


import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.StepTransDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/step-trans-dtl")
public class StepTransDetailsController {
    @Autowired
    private StepTransDetailsService stepTransDetailsService;

    @PostMapping("/create")
    public ResponseEntity<ApiRequestResponse> create(
            @RequestParam String userName,
            @RequestBody StepTransDetailsRequest stepTransRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.stepTransDetailsService.create(userName, stepTransRequest));
    }

    @GetMapping("/findAllByStepTransId")
    public ResponseEntity<ApiRequestResponse> findAllByStepTransId(@RequestParam Long stepTransId) {
        return ResponseEntity.ok().body(this.stepTransDetailsService.findAllByStepTransId(stepTransId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.stepTransDetailsService.findById(id));
    }
    
}

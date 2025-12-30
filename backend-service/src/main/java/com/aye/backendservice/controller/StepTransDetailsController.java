package com.aye.backendservice.controller;


import com.aye.backendservice.service.StepTransDetailsService;
import com.aye.commonlib.dto.request.StepTransDetailsLinesRequest;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(this.stepTransDetailsService.save(stepTransRequest, userName));
    }

    @GetMapping("/findAllByStepTransId")
    public ResponseEntity<ApiRequestResponse> findAllByStepTransId(@RequestParam Long stepTransId) {
        return ResponseEntity.ok().body(this.stepTransDetailsService.findAllByStepTransId(stepTransId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.stepTransDetailsService.findById(id));
    }


    //***** Line Section ********

    @PostMapping("/create-dtl")
    public ResponseEntity<ApiRequestResponse> saveStDtlLine(@Valid @RequestBody StepTransDetailsLinesRequest stepTrnsDtlLnsReq,
                                                            @RequestParam String userName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.stepTransDetailsService
                .saveStDtlLine(stepTrnsDtlLnsReq, userName));
    }

    @GetMapping("/findStDtlLineById")
    public ResponseEntity<ApiRequestResponse> findStDtlLineById(@RequestParam Long stepTransDtlLnId) {
        return ResponseEntity.ok().body(this.stepTransDetailsService.findStDtlLineById(stepTransDtlLnId));
    }

    @GetMapping("/findAllByStTrnDtlId")
    public ResponseEntity<ApiRequestResponse> findAllByStTrnDtlId(@RequestParam Long stepTransDtlId) {
        return ResponseEntity.ok().body(this.stepTransDetailsService.findAllByStTrnDtlId(stepTransDtlId));
    }
}

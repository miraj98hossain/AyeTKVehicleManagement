package com.aye.backendservice.controller;


import com.aye.backendservice.service.StepTransDetailsViewService;
import com.aye.dtoLib.dto.request.StepTransDetailsLinesRequest;
import com.aye.dtoLib.dto.request.StepTransDetailsRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/step-trans-dtl")
public class StepTransDetailsController {
    @Autowired
    private StepTransDetailsViewService stepTransDetailsService;

    @PostMapping("/create")
    public ResponseEntity<ApiRequestResponse> create(
            @RequestParam String userName,
            @RequestBody StepTransDetailsRequest stepTransRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.stepTransDetailsService.save(stepTransRequest, userName));
    }

    @GetMapping("/findAllByStepTransId")
    public ResponseEntity<ApiRequestResponse> findAllByStepTransId(@RequestParam Long stepTransId) {
        var res = this.stepTransDetailsService.findAllByStepTransId(stepTransId);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.stepTransDetailsService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> deleteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.stepTransDetailsService.deleteById(id));
    }


    //***** Line Section ********
    @PostMapping("loadingStatusUpdate/{stepTransLineId}/{detailLineId}")
    public ResponseEntity<ApiRequestResponse> loadingStatusUpdate(@PathVariable Long stepTransLineId,
                                                                  @PathVariable Long detailLineId,
                                                                  @RequestParam String stepStatus,
                                                                  @RequestParam String userName) throws ExecutionControl.NotImplementedException {

        return ResponseEntity.status(HttpStatus.OK).body(this.stepTransDetailsService.loadingStatusUpdate(stepTransLineId, detailLineId, stepStatus, userName));

    }

    @PostMapping("/create-dtl")
    public ResponseEntity<ApiRequestResponse> saveStDtlLine(@Valid @RequestBody StepTransDetailsLinesRequest stepTrnsDtlLnsReq,
                                                            @RequestParam String userName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.stepTransDetailsService
                .saveLine(stepTrnsDtlLnsReq, userName));
    }

    @GetMapping("/findStDtlLineById")
    public ResponseEntity<ApiRequestResponse> findStDtlLineById(@RequestParam Long stepTransDtlLnId) {
        return ResponseEntity.ok().body(this.stepTransDetailsService.findStDtlLineById(stepTransDtlLnId));
    }

    @GetMapping("/findAllLinesByStepTransIdAndUserItemAccess")
    public ResponseEntity<ApiRequestResponse> findAllLinesByStepTransIdAndUserItemAccess(@RequestParam Long stepTransId, @RequestParam String userName) {
        var res = this.stepTransDetailsService.findAllLinesByStepTransIdAndUserItemAccess(stepTransId, userName);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/findAllByStTrnDtlId")
    public ResponseEntity<ApiRequestResponse> findAllByStTrnDtlId(@RequestParam Long stepTransDtlId) {
        return ResponseEntity.ok().body(this.stepTransDetailsService.findAllByStTrnDtlId(stepTransDtlId));
    }

    @DeleteMapping("/deleteLineById/{id}")
    public ResponseEntity<ApiRequestResponse> deleteLineById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.stepTransDetailsService.deleteLineById(id));
    }
}

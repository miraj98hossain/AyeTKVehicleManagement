package com.aye.backendservice.controller;


import com.aye.backendservice.service.StepTransDetailsService;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
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

    //    @GetMapping("/findAllByTempDtlId")
//    public ResponseEntity<ApiRequestResponse> findAllByTempDtlId(@RequestParam Integer tempDtlId,
//                                                                 @RequestParam(required = false) String searchWords,
//                                                                 @PageableDefault(size = 10, page = 0) Pageable pageable) {
//        return ResponseEntity.ok().body(this.stepTransService.findAllByTempDtlId(tempDtlId, searchWords, pageable));
//    }

    //    @PostMapping("/update-lines")
//    public ResponseEntity<ApiRequestResponse> updateLines(
//            @RequestParam String userName,
//            @Validated({StepTransLinesUpdateValidation.class, Default.class})
//            @RequestBody StepTransLinesRequest stepTransLinesRequest) {
//        return ResponseEntity.ok().body(this.stepTransService.updateTransLines(stepTransLinesRequest, userName));
//    }
}

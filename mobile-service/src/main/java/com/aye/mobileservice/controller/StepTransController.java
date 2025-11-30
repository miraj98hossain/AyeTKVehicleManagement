package com.aye.mobileservice.controller;


import com.aye.commonlib.dto.request.StepTransLinesRequest;
import com.aye.commonlib.dto.request.StepTransRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.mobileservice.service.StepTransService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/step-trans")
public class StepTransController {
    @Autowired
    private StepTransService stepTransService;

    @PostMapping("/create")
    public ResponseEntity<ApiRequestResponse> create(
            @RequestBody StepTransRequest stepTransRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.status(HttpStatus.CREATED).body(this.stepTransService.create(authentication.getName(), stepTransRequest));
    }

    @PostMapping("/update-lines")
    public ResponseEntity<ApiRequestResponse> updateLines(
            @RequestBody StepTransLinesRequest stepTransLinesRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(this.stepTransService.updateLines(authentication.getName(), stepTransLinesRequest));
    }

    @GetMapping
    public ResponseEntity<ApiRequestResponse> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.stepTransService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.stepTransService.findById(id));
    }

    @GetMapping("/findAllByTempDtlId")
    public ResponseEntity<ApiRequestResponse> findAllByTempDtlId(@RequestParam Integer tempDtlId,
                                                                 @RequestParam(required = false) String searchWords,
                                                                 @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return ResponseEntity.ok().body(this.stepTransService.findAllByTempDtlId(tempDtlId, searchWords, pageable));
    }
}

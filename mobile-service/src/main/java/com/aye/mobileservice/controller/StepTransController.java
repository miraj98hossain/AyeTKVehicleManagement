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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/step-trans")
public class StepTransController {
    @Autowired
    private StepTransService stepTransService;

    @PostMapping("/create")
    public ResponseEntity<ApiRequestResponse> create(
            @RequestBody StepTransRequest stepTransRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.stepTransService.create(stepTransRequest));
    }

    @PostMapping("/update-lines")
    public ResponseEntity<ApiRequestResponse> updateLines(
            @RequestBody StepTransLinesRequest stepTransLinesRequest) {
        return ResponseEntity.ok().body(this.stepTransService.updateLines(stepTransLinesRequest));
    }

    @GetMapping
    public ResponseEntity<ApiRequestResponse> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.stepTransService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.stepTransService.findById(id));
    }

    @GetMapping("/findAllBySetupDtls")
    public ResponseEntity<ApiRequestResponse> findAllBySetupDtls(@RequestParam List<Long> setupDetailIds,
                                                                 @RequestParam(required = false) String searchWords,
                                                                 @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return ResponseEntity.ok().body(this.stepTransService.findAllBySetupDtls(setupDetailIds, searchWords, pageable));
    }
}

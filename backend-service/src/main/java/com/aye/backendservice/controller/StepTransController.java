package com.aye.backendservice.controller;


import com.aye.backendservice.service.StepTransService;
import com.aye.commonlib.dto.request.StepTransLinesRequest;
import com.aye.commonlib.dto.request.StepTransRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.validationGroup.StepTransCreateValidation;
import com.aye.commonlib.dto.validationGroup.StepTransLinesUpdateValidation;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/step-trans")
public class StepTransController {
    @Autowired
    private StepTransService stepTransService;

    @PostMapping("/create")
    public ResponseEntity<ApiRequestResponse> create(
            @RequestParam Long currentUserId,
            @Validated({StepTransCreateValidation.class, Default.class})
            @RequestBody StepTransRequest stepTransRequest,
            UriComponentsBuilder uriBuilder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.stepTransService.saveStepTrans(stepTransRequest, currentUserId));
    }

    @PostMapping("/update-lines")
    public ResponseEntity<ApiRequestResponse> updateLines(
            @RequestParam Long currentUserId,
            @Validated({StepTransLinesUpdateValidation.class, Default.class})
            @RequestBody StepTransLinesRequest stepTransLinesRequest) {
        return ResponseEntity.ok().body(this.stepTransService.updateTransLines(stepTransLinesRequest, currentUserId));
    }

    @GetMapping
    public ResponseEntity<ApiRequestResponse> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.stepTransService.findAll(pageable));
    }

    @GetMapping("/findAllBySetupDtls")
    public ResponseEntity<ApiRequestResponse> findAllBySetupDtls(@RequestParam List<Long> setupDetailIds,
                                                                 @RequestParam(required = false) String searchWords,
                                                                 @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return ResponseEntity.ok().body(this.stepTransService.findAllBySetupDtls(setupDetailIds, searchWords, pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.stepTransService.findById(id));
    }
}

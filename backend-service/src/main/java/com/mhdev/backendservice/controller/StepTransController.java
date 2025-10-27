package com.mhdev.backendservice.controller;


import com.mhdev.backendservice.service.StepTransService;
import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.StepTransLinesResponse;
import com.mhdev.commonlib.dto.response.StepTransResponse;
import com.mhdev.commonlib.dto.validationGroup.StepTransCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepTransLinesUpdateValidation;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/step-trans")
public class StepTransController {
    @Autowired
    private StepTransService stepTransService;

    @PostMapping("/create")
    public ResponseEntity<StepTransResponse> create(@Validated({StepTransCreateValidation.class, Default.class}) @RequestBody StepTransRequest stepTransRequest, UriComponentsBuilder uriBuilder) {
        StepTransResponse stepTransResponse = this.stepTransService.saveStepTrans(stepTransRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(stepTransResponse);
    }

    @PostMapping("/update-lines")
    public ResponseEntity<StepTransLinesResponse> updateLines(@Validated({StepTransLinesUpdateValidation.class, Default.class}) @RequestBody StepTransLinesRequest stepTransLinesRequest) {
        StepTransLinesResponse stepTransLinesResponse = this.stepTransService.updateTransLines(stepTransLinesRequest);
        return ResponseEntity.ok().body(stepTransLinesResponse);
    }

    @GetMapping
    public ResponseEntity<Page<StepTransResponse>> findAll(Pageable pageable) {
        Page<StepTransResponse> list = this.stepTransService.findAll(pageable);
        if (list == null || list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StepTransResponse> findById(@PathVariable("id") Long id) {
        StepTransResponse stepTransResponse = this.stepTransService.findById(id);
        return ResponseEntity.ok().body(stepTransResponse);
    }
}

package com.mhdev.backendservice.controller;


import com.mhdev.backendservice.service.StepTransService;
import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import com.mhdev.commonlib.dto.validationGroup.StepTransCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepTransLinesUpdateValidation;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ApiRequestResponse> create(@Validated({StepTransCreateValidation.class, Default.class}) @RequestBody StepTransRequest stepTransRequest, UriComponentsBuilder uriBuilder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.stepTransService.saveStepTrans(stepTransRequest));
    }

    @PostMapping("/update-lines")
    public ResponseEntity<ApiRequestResponse> updateLines(@Validated({StepTransLinesUpdateValidation.class, Default.class}) @RequestBody StepTransLinesRequest stepTransLinesRequest) {
        return ResponseEntity.ok().body(this.stepTransService.updateTransLines(stepTransLinesRequest));
    }

    @GetMapping
    public ResponseEntity<ApiRequestResponse> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.stepTransService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.stepTransService.findById(id));
    }
}

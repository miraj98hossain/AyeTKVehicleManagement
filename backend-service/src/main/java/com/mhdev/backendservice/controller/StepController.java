package com.mhdev.backendservice.controller;

import com.mhdev.backendservice.service.StepService;
import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import com.mhdev.commonlib.dto.validationGroup.StepCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepUpdateValidation;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/steps")
public class StepController {
    @Autowired
    private StepService stepService;

    @PostMapping("/save")
    public ResponseEntity<ApiRequestResponse> saveStep(@Validated({StepCreateValidation.class, Default.class}) @RequestBody StepRequest stepRequest) {
        return ResponseEntity.ok().body(this.stepService.saveStep(stepRequest));
    }

    @PutMapping("/save")
    public ResponseEntity<ApiRequestResponse> updateStep(@Validated({StepUpdateValidation.class}) @RequestBody StepRequest stepRequest) {
        return ResponseEntity.ok().body(this.stepService.saveStep(stepRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> getStep(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.stepService.getStep(id));
    }

    @GetMapping()
    public ResponseEntity<ApiRequestResponse> getSteps(Pageable pageable) {
        var list = this.stepService.getAllSteps(pageable);
        if (list == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

}

package com.aye.backendservice.controller;

import com.aye.backendservice.service.StepService;
import com.aye.commonlib.dto.request.StepRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.validationGroup.StepCreateValidation;
import com.aye.commonlib.dto.validationGroup.StepUpdateValidation;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/steps")
public class StepController {
    @Autowired
    private StepService stepService;

    @PostMapping("/save")
    public ResponseEntity<ApiRequestResponse> saveStep(
            @RequestParam String currentUserName,
            @Validated({StepCreateValidation.class, Default.class})
            @RequestBody StepRequest stepRequest) {
        return ResponseEntity.ok().body(this.stepService.saveStep(stepRequest, currentUserName));
    }

    @PutMapping("/save")
    public ResponseEntity<ApiRequestResponse> updateStep(
            @RequestParam String currentUserName,
            @Validated({StepUpdateValidation.class})
            @RequestBody StepRequest stepRequest) {
        return ResponseEntity.ok().body(this.stepService.saveStep(stepRequest, currentUserName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> getStep(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.stepService.getStep(id));
    }

    @GetMapping
    public ResponseEntity<ApiRequestResponse> getSteps(
            Pageable pageable,
            @RequestParam(required = false) String searchWords) {

        if (searchWords != null && !searchWords.isBlank()) {
            var list = stepService.getSearchedSteps(searchWords);
            return list == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
        } else {
            var list = stepService.getAllSteps(pageable);
            return list == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
        }
    }
//    @GetMapping
//    public ResponseEntity<List<Step>> getSteps() {
//        return ResponseEntity.ok(this.stepService.getAllSteps());
//    }


}

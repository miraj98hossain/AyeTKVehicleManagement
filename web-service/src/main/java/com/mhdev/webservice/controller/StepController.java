package com.mhdev.webservice.controller;


import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import com.mhdev.commonlib.dto.validationGroup.StepCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepUpdateValidation;
import com.mhdev.webservice.service.StepService;
import jakarta.validation.groups.Default;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/steps")
public class StepController {
    @Autowired
    private StepService stepService;

    @PostMapping("/save")
    public ResponseEntity<ApiRequestResponse> saveStep(
            @RequestParam Long currentUserId,
            @Validated({StepCreateValidation.class, Default.class})
            @RequestBody StepRequest stepRequest) {
        return ResponseEntity.ok().body(this.stepService.saveStep(stepRequest, currentUserId));
    }

    @PutMapping("/save")
    public ResponseEntity<ApiRequestResponse> updateStep(
            @RequestParam Long currentUserId,
            @Validated({StepUpdateValidation.class})
            @RequestBody StepRequest stepRequest) {

        return ResponseEntity.ok().body(this.stepService.saveStep(stepRequest, currentUserId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> getStep(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.stepService.getStep(id));
    }

    @GetMapping()
    public ResponseEntity<ApiRequestResponse> getSteps(@RequestParam(required = false) String searchWords, Pageable pageable) {
        var list = this.stepService.getSteps(searchWords, pageable);
        if (list == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

}

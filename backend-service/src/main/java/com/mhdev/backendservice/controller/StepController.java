package com.mhdev.backendservice.controller;

import com.mhdev.backendservice.service.StepService;
import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.StepResponse;
import com.mhdev.commonlib.dto.validationGroup.StepCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepUpdateValidation;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/steps")
public class StepController {
    @Autowired
    private StepService stepService;

    @PostMapping("/save")
    public ResponseEntity<StepResponse> saveStep(@Validated({StepCreateValidation.class, Default.class})@RequestBody StepRequest stepRequest, UriComponentsBuilder uriBuilder) {
        try {
            StepResponse savedStep = this.stepService.saveStep(stepRequest);
            URI location = uriBuilder
                    .path("/steps/{id}")
                    .buildAndExpand(savedStep.getStepId())
                    .toUri();

            return ResponseEntity.created(location).body(savedStep);
        } catch (Exception e) {

            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @PutMapping("/save")
    public ResponseEntity<StepResponse> updateStep(@Validated({StepUpdateValidation.class})@RequestBody StepRequest stepRequest) {
        try {
            StepResponse savedStep = this.stepService.saveStep(stepRequest);
            return ResponseEntity.ok().body(savedStep);
        } catch (Exception e) {

            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StepResponse> getStep(@PathVariable("id")Long id) {
        try {
            StepResponse savedStep = this.stepService.getStep(id);
            return ResponseEntity.ok().body(savedStep);
        } catch (Exception e) {

            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity<Page<StepResponse>> getSteps(Pageable pageable) {
        try {
             var list =this.stepService.getAllSteps(pageable);
             if(list==null||list.isEmpty()){
                 return ResponseEntity.noContent().build();
             }
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

}

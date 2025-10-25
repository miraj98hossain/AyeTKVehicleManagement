package com.mhdev.webservice.controller;


import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.StepTransLinesResponse;
import com.mhdev.commonlib.dto.response.StepTransResponse;
import com.mhdev.webservice.service.StepTransService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/step-trans")
public class StepTransController {
    @Autowired
    private StepTransService stepTransService;

    @PostMapping("/create")
    public ResponseEntity<StepTransResponse> create(@RequestBody StepTransRequest stepTransRequest, UriComponentsBuilder uriBuilder) {
        try {
            StepTransResponse stepTransResponse = this.stepTransService.create(stepTransRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(stepTransResponse);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/update-lines")
    public ResponseEntity<StepTransLinesResponse> updateLines(@RequestBody StepTransLinesRequest stepTransLinesRequest) {
        try {
            StepTransLinesResponse stepTransLinesResponse = this.stepTransService.updateLines(stepTransLinesRequest);
            return ResponseEntity.ok().body(stepTransLinesResponse);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<StepTransResponse>> findAll(Pageable pageable) {
        try {
            Page<StepTransResponse> list = this.stepTransService.findAll(pageable);
            if (list == null || list.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StepTransResponse> findById(@PathVariable("id") Long id) {
        try {
            StepTransResponse stepTransResponse = this.stepTransService.findById(id);
            return ResponseEntity.ok().body(stepTransResponse);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

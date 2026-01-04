package com.aye.webservice.controller;


import com.aye.commonlib.dto.request.AppModuleRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.AppModuleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app-module")
public class AppModuleController {
    @Autowired
    AppModuleService appModuleService;

    @GetMapping("/findByCode")
    ResponseEntity<ApiRequestResponse> findByCode(@RequestParam("appModuleCode") String appModuleCode) {
        return ResponseEntity.ok(this.appModuleService.findByCode(appModuleCode));
    }

    @GetMapping("/findAll")
    ResponseEntity<ApiRequestResponse> findAll() {
        return ResponseEntity.ok(this.appModuleService.findAll());
    }

    @GetMapping("/findById/{id}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.appModuleService.findById(id));
    }

    @GetMapping("/findByIds")
    public ResponseEntity<ApiRequestResponse> findByIds(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(this.appModuleService.findByIds(ids));
    }

    @PostMapping("/saveAppmodule")
    ResponseEntity<ApiRequestResponse> saveAppmodule(@Valid @RequestBody AppModuleRequest appModuleRequest) {
        return ResponseEntity.ok(this.appModuleService.saveAppmodule(appModuleRequest));
    }
}

package com.aye.backendservice.controller;

import com.aye.backendservice.service.AppModuleBService;
import com.aye.commonlib.dto.request.AppModuleRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/app-module")
public class AppModuleController {
    @Autowired
    AppModuleBService appModuleBService;

    @GetMapping("/findByCode")
    ResponseEntity<ApiRequestResponse> findByCode(@RequestParam("appModuleCode") String appModuleCode) {
        return ResponseEntity.ok(this.appModuleBService.findByCode(appModuleCode));
    }

    @GetMapping("/findAll")
    ResponseEntity<ApiRequestResponse> findAll() {
        return ResponseEntity.ok(this.appModuleBService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.appModuleBService.findById(id));
    }

    @PostMapping("/saveAppmodule")
    public ResponseEntity<ApiRequestResponse> saveAppmodule(@Valid @RequestBody AppModuleRequest appModuleRequest) {
        return ResponseEntity.ok(this.appModuleBService.saveAppmodule(appModuleRequest));
    }
}

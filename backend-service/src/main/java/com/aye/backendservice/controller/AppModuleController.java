package com.aye.backendservice.controller;

import com.aye.RestfulServer.model.common.AppModuleCode;
import com.aye.backendservice.service.AppModuleBService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app-module")
public class AppModuleController {
    @Autowired
    AppModuleBService appModuleBService;

    @GetMapping("/findByCode")
    ResponseEntity<ApiRequestResponse> findByCode(@RequestBody AppModuleCode appModuleCode) {
        return ResponseEntity.ok(this.appModuleBService.findByCode(appModuleCode));
    }

    @GetMapping("findAll")
    ResponseEntity<ApiRequestResponse> findAll() {
        return ResponseEntity.ok(this.appModuleBService.findAll());
    }
}

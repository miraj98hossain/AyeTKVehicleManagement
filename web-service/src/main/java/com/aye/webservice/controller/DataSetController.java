package com.aye.webservice.controller;

import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.concurrency.DataSetDto;
import com.aye.webservice.service.DataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Miraj
 * @date: 11/08/2026
 * @time: 4:51 pm
 */
@RestController
@RequestMapping("/api/data-set")
public class DataSetController {
    @Autowired
    private DataSetService dataSetService;

    @GetMapping("/getAllDataSet")
    public ResponseEntity<ApiRequestResponse> getAllDataSet() {
        return ResponseEntity.ok(dataSetService.getAllDataSet());
    }

    @PostMapping("/create")
    public ResponseEntity<ApiRequestResponse> saveDataSet(@RequestBody DataSetDto dataSetMDT) {
        return ResponseEntity.ok(dataSetService.saveDataSet(dataSetMDT));
    }

    @GetMapping("/getDataSetById/{dataSetId}")
    public ResponseEntity<ApiRequestResponse> getDataSetById(@PathVariable Long dataSetId) {
        return ResponseEntity.ok(dataSetService.getDataSetById(dataSetId));
    }

}

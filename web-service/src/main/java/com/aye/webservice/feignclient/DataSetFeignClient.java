package com.aye.webservice.feignclient;

import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.concurrency.DataSetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "DataSetFeignClient", url = "${backend.service.url}/api/data-set")
public interface DataSetFeignClient {

    @GetMapping("/getAllDataSet")
    ResponseEntity<ApiRequestResponse> getAllDataSet();

    @PostMapping("/create")
    ResponseEntity<ApiRequestResponse> saveDataSet(@RequestBody DataSetDto dataSetMDT);

    @GetMapping("/getDataSetById/{dataSetId}")
    ResponseEntity<ApiRequestResponse> getDataSetById(@PathVariable Long dataSetId);


}
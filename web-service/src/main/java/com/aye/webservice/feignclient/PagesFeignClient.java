package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.request.PagesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PagesFeignClient",
        url = "${backend.service.url}/api/pages")
public interface PagesFeignClient {

    @GetMapping("/findAll")
    ResponseEntity<ApiRequestResponse> findAll();

    @GetMapping("/findById/{id}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id);

    @GetMapping("/findByName")
    ResponseEntity<ApiRequestResponse> findByName(@RequestParam String name);

    @PostMapping("/save")
    ResponseEntity<ApiRequestResponse> save(@Valid @RequestBody PagesRequest pagesRequest);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ApiRequestResponse> delete(@PathVariable("id") Long id);

    @GetMapping("/findByUrl")
    ResponseEntity<ApiRequestResponse> findByUrl(@RequestParam String p);

    @GetMapping("/findByPageGroup/{group}")
    ResponseEntity<ApiRequestResponse> findByPageGroup(@PathVariable("group") Integer group);
}

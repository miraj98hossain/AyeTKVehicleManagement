package com.aye.webservice.controller;

import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.user.RequestGroupHeaderDto;
import com.aye.dto.response.user.RequestGroupLineDto;
import com.aye.webservice.service.RequestGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/request-group")
public class RequestHeaderController {
    @Autowired
    private RequestGroupService requestGroupService;


    @GetMapping("/getAllRequestGroupHeader")
    ResponseEntity<ApiRequestResponse> getAllRequestGroupHeader() {
        return new ResponseEntity<>(requestGroupService.getAllRequestGroupHeader(), HttpStatus.OK);
    }

    @GetMapping("/getRequestGroupHeader/{headerId}")
    ResponseEntity<ApiRequestResponse> getRequestGroupHeaderById(@PathVariable Long headerId) {
        return new ResponseEntity<>(requestGroupService.getRequestGroupHeaderById(headerId), HttpStatus.OK);
    }

    @PostMapping("/saveRequestGroupHeader/{userName}")
    ResponseEntity<ApiRequestResponse> saveRequestGroupHeader(@RequestBody RequestGroupHeaderDto requestGroupHeaderDto, @PathVariable String userName) {
        return new ResponseEntity<>(requestGroupService.saveRequestGroupHeader(requestGroupHeaderDto, userName), HttpStatus.OK);
    }

    // not Api response
    @GetMapping("/getRequestGroupLineByLineId/{lineId}")
    ResponseEntity<ApiRequestResponse> getRequestGroupLineById(@PathVariable Long lineId) {
        return new ResponseEntity<>(requestGroupService.getRequestGroupLineById(lineId), HttpStatus.OK);
    }

    @GetMapping("/getRequestGroupLinesByRequestGroupGeaderId/{headerId}")
    ResponseEntity<ApiRequestResponse> getAllRequestGroupLineByHeaderId(@PathVariable Long headerId) {
        return new ResponseEntity<>(requestGroupService.getAllRequestGroupLineByHeaderId(headerId), HttpStatus.OK);
    }

    @PostMapping("/saveRequestGroupLine/{userName}")
    ResponseEntity<ApiRequestResponse> saveRequestGroupLine(@RequestBody RequestGroupLineDto requestGroupLineDto, @PathVariable String userName) {
        return new ResponseEntity<>(requestGroupService.saveRequestGroupLine(requestGroupLineDto, userName), HttpStatus.OK);
    }
}

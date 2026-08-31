package com.aye.backendservice.controller;

import com.aye.backendservice.service.concurrency.RequestGroupViewService;
import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.user.RequestGroupHeaderDto;
import com.aye.dto.response.user.RequestGroupLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/request-group")
public class RequestGroupController {

    @Autowired
    private RequestGroupViewService reqGrpHdrService;

    @GetMapping("/getAllRequestGroupHeader")
    ResponseEntity<ApiRequestResponse> getAllRequestGroupHeader() {
        return new ResponseEntity<>(reqGrpHdrService.getAllRequestGroupHeader(), HttpStatus.OK);
    }

    @GetMapping("/getRequestGroupHeader/{headerId}")
    ResponseEntity<ApiRequestResponse> getRequestGroupHeaderById(@PathVariable Long headerId) {
        return new ResponseEntity<>(reqGrpHdrService.getRequestGroupHeaderById(headerId), HttpStatus.OK);
    }

    @PostMapping("/saveRequestGroupHeader/{userName}")
    ResponseEntity<ApiRequestResponse> saveRequestGroupHeader(@RequestBody RequestGroupHeaderDto requestGroupHeaderDto, @PathVariable String userName) {
        return new ResponseEntity<>(reqGrpHdrService.saveRequestGroupHeader(requestGroupHeaderDto, userName), HttpStatus.OK);
    }

    // not Api response
    @GetMapping("/getRequestGroupLineByLineId/{lineId}")
    ResponseEntity<ApiRequestResponse> getRequestGroupLineById(@PathVariable Long lineId) {
        return new ResponseEntity<>(reqGrpHdrService.getRequestGroupLineById(lineId), HttpStatus.OK);
    }

    @GetMapping("/getRequestGroupLinesByRequestGroupGeaderId/{headerId}")
    ResponseEntity<ApiRequestResponse> getAllRequestGroupLineByHeaderId(@PathVariable Long headerId) {
        return new ResponseEntity<>(reqGrpHdrService.getAllRequestGroupLineByHeaderId(headerId), HttpStatus.OK);
    }

    @PostMapping("/saveRequestGroupLine/{userName}")
    ResponseEntity<ApiRequestResponse> saveRequestGroupLine(@RequestBody RequestGroupLineDto requestGroupLineDto, @PathVariable String userName) {
        return new ResponseEntity<>(reqGrpHdrService.saveRequestGroupLine(requestGroupLineDto, userName), HttpStatus.OK);
    }

}

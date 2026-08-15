//package com.aye.backendservice.controller;
//
//import com.aye.backendservice.service.RequestGroupHeaderBService;
//import com.aye.dto.response.ApiRequestResponse;
//import com.aye.dto.response.user.RequestGroupHeaderDto;
//import com.aye.dto.response.user.RequestGroupLineDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/request-group")
//public class RequestGroupController {
//
//    @Autowired
//    RequestGroupHeaderBService reqGrpHdrService;
//
//    @GetMapping("/findAllRequestGroup")
//    public ResponseEntity<ApiRequestResponse> findAllRequestGroup() {
//        return new ResponseEntity<>(reqGrpHdrService.findAllRequestGroup(), HttpStatus.OK);
//    }
//
//    @GetMapping("/findReqGrpById/{id}")
//    public ResponseEntity<ApiRequestResponse> findReqGrpById(@PathVariable("id") Long id) {
//        return ResponseEntity.ok().body(this.reqGrpHdrService.findReqGrpById(id));
//    }
//
//    @PostMapping("/saveReqGrp")
//    public ResponseEntity<ApiRequestResponse> saveReqGrp(@RequestBody RequestGroupHeaderDto requestGroupHeader) {
//        return ResponseEntity.ok().body(this.reqGrpHdrService.saveReqGrp(requestGroupHeader));
//    }
//
//    @GetMapping("/getUserReport/{reqGrpId}")
//    public ResponseEntity<ApiRequestResponse> getUserReport(@PathVariable("reqGrpId") Long reqGrpId) {
//        return ResponseEntity.ok().body(this.reqGrpHdrService.getUserReport(reqGrpId));
//    }
//
//    @PostMapping("/saveReqGrpLine")
//    public ResponseEntity<ApiRequestResponse> saveReqGrpLine(@RequestBody RequestGroupLineDto requestGroupLine) {
//        return ResponseEntity.ok().body(this.reqGrpHdrService.saveReqGrpLine(requestGroupLine));
//    }
//
//    @GetMapping("/getAllLines")
//    public ResponseEntity<ApiRequestResponse> getAllLines() {
//        return ResponseEntity.ok().body(this.reqGrpHdrService.getAllLines());
//    }
//
//    @GetMapping("/findByLineId/{id}")
//    public ResponseEntity<ApiRequestResponse> findByLineId(@PathVariable("id") Long id) {
//        return ResponseEntity.ok().body(this.reqGrpHdrService.findByLineId(id));
//    }
//
//    @GetMapping("/findByrequestGroupHeader/{reqGrpHdrId}")
//    public ResponseEntity<ApiRequestResponse> findByrequestGroupHeader(@PathVariable("reqGrpHdrId") Long reqGrpHdrId) {
//        return ResponseEntity.ok().body(this.reqGrpHdrService.findByrequestGroupHeader(reqGrpHdrId));
//
//    }
//
//    @GetMapping("/findActByReqGrpHdr/{reqGrpHdrId}")
//    public ResponseEntity<ApiRequestResponse> findActiveByRequestGroupHeader(@PathVariable("reqGrpHdrId") Long reqGrpHdrId) {
//        return ResponseEntity.ok().body(this.reqGrpHdrService.findActiveByRequestGroupHeader(reqGrpHdrId));
//    }
//
//
//    @GetMapping("/findByExecutables/{executablesId}")
//    public ResponseEntity<ApiRequestResponse> findByExecutables(@PathVariable("executablesId") Long executablesId) {
//        return ResponseEntity.ok().body(this.reqGrpHdrService.findByExecutables(executablesId));
//    }
//}

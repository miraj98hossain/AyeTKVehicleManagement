package com.aye.webservice.controller;

//@RestController
//@RequestMapping("/api/request-group")
//public class RequestHeaderController {
//    @Autowired
//    private RequestGroupService requestGroupService;
//
//    @GetMapping("/findAllRequestGroup")
//    public ResponseEntity<ApiRequestResponse> findAllRequestGroup() {
//        return ResponseEntity.ok(requestGroupService.findAllRequestGroup());
//    }
//
//    @GetMapping("/findReqGrpById/{id}")
//    public ResponseEntity<ApiRequestResponse> findReqGrpById(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(requestGroupService.findReqGrpById(id));
//    }
//
//    @PostMapping("/saveReqGrp")
//    public ResponseEntity<ApiRequestResponse> saveReqGrp(@Valid @RequestBody
//                                                         RequestGroupHeaderRequest requestGroupHeader) {
//        return ResponseEntity.ok(requestGroupService.saveReqGrp(requestGroupHeader));
//    }
//
//    @GetMapping("/getUserReport/{reqGrpId}")
//    public ResponseEntity<ApiRequestResponse> getUserReport(@PathVariable("reqGrpId") Long reqGrpId) {
//        return ResponseEntity.ok(requestGroupService.getUserReport(reqGrpId));
//    }
//
//    @PostMapping("/saveReqGrpLine")
//    public ResponseEntity<ApiRequestResponse> saveReqGrpLine(@Valid @RequestBody
//                                                             RequestGroupLineRequest requestGroupLine) {
//        return ResponseEntity.ok(requestGroupService.saveReqGrpLine(requestGroupLine));
//    }
//
//    @GetMapping("/getAllLines")
//    public ResponseEntity<ApiRequestResponse> getAllLines() {
//        return ResponseEntity.ok(requestGroupService.getAllLines());
//    }
//
//    @GetMapping("/findByLineId/{id}")
//    public ResponseEntity<ApiRequestResponse> findByLineId(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(requestGroupService.findByLineId(id));
//    }
//
//    @GetMapping("/findByrequestGroupHeader/{reqGrpHdrId}")
//    public ResponseEntity<ApiRequestResponse> findByrequestGroupHeader(@PathVariable("reqGrpHdrId")
//                                                                       Long reqGrpHdrId) {
//        return ResponseEntity.ok(requestGroupService.findByrequestGroupHeader(reqGrpHdrId));
//    }
//
//    @GetMapping("/findActByReqGrpHdr/{reqGrpHdrId}")
//    public ResponseEntity<ApiRequestResponse> findActiveByRequestGroupHeader(@PathVariable("reqGrpHdrId")
//                                                                             Long reqGrpHdrId) {
//        return ResponseEntity.ok(requestGroupService.findActiveByRequestGroupHeader(reqGrpHdrId));
//    }
//
//
//    @GetMapping("/findByExecutables/{executablesId}")
//    public ResponseEntity<ApiRequestResponse> findByExecutables(@PathVariable("executablesId")
//                                                                Long executablesId) {
//        return ResponseEntity.ok(requestGroupService.findByExecutables(executablesId));
//    }
//}

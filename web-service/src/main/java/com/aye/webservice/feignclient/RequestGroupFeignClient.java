package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.request.RequestGroupHeaderRequest;
import com.aye.commonlib.dto.request.RequestGroupLineRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "OrgHierarchyFeignClient",
        url = "${backend.service.url}/api/request-group")
public interface RequestGroupFeignClient {
    @GetMapping("/findAllRequestGroup")
    ResponseEntity<ApiRequestResponse> findAllRequestGroup();

    @GetMapping("/findReqGrpById/{id}")
    ResponseEntity<ApiRequestResponse> findReqGrpById(@PathVariable("id") Long id);

    @PostMapping("/saveReqGrp")
    ResponseEntity<ApiRequestResponse> saveReqGrp(@Valid @RequestBody
                                                  RequestGroupHeaderRequest requestGroupHeader);

    @GetMapping("/getUserReport/{reqGrpId}")
    ResponseEntity<ApiRequestResponse> getUserReport(@PathVariable("reqGrpId") Long reqGrpId);

    @PostMapping("/saveReqGrpLine")
    ResponseEntity<ApiRequestResponse> saveReqGrpLine(@Valid @RequestBody
                                                      RequestGroupLineRequest requestGroupLine);

    @GetMapping("/getAllLines")
    ResponseEntity<ApiRequestResponse> getAllLines();

    @GetMapping("/findByLineId/{id}")
    ResponseEntity<ApiRequestResponse> findByLineId(@PathVariable("id") Long id);

    @GetMapping("/findByrequestGroupHeader/{reqGrpHdrId}")
    ResponseEntity<ApiRequestResponse> findByrequestGroupHeader(@PathVariable("reqGrpHdrId")
                                                                Long reqGrpHdrId);

    @GetMapping("/findActByReqGrpHdr/{reqGrpHdrId}")
    ResponseEntity<ApiRequestResponse> findActiveByRequestGroupHeader(@PathVariable("reqGrpHdrId")
                                                                      Long reqGrpHdrId);


    @GetMapping("/findByExecutables/{executablesId}")
    ResponseEntity<ApiRequestResponse> findByExecutables(@PathVariable("executablesId")
                                                         Long executablesId);
}

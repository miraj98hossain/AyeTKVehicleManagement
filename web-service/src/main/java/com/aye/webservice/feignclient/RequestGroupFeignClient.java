package com.aye.webservice.feignclient;

import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.user.RequestGroupHeaderDto;
import com.aye.dto.response.user.RequestGroupLineDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "RequestGroupFeignClient",
        url = "${backend.service.url}/api/request-group")
public interface RequestGroupFeignClient {


    @GetMapping("/getAllRequestGroupHeader")
    ResponseEntity<ApiRequestResponse> getAllRequestGroupHeader();

    @GetMapping("/getRequestGroupHeader/{headerId}")
    ResponseEntity<ApiRequestResponse> getRequestGroupHeaderById(@PathVariable Long headerId);

    @PostMapping("/saveRequestGroupHeader/{userName}")
    ResponseEntity<ApiRequestResponse> saveRequestGroupHeader(@RequestBody RequestGroupHeaderDto requestGroupHeaderDto, @PathVariable String userName);

    // not Api response
    @GetMapping("/getRequestGroupLineByLineId/{lineId}")
    ResponseEntity<ApiRequestResponse> getRequestGroupLineById(@PathVariable Long lineId);

    @GetMapping("/getRequestGroupLinesByRequestGroupGeaderId/{headerId}")
    ResponseEntity<ApiRequestResponse> getAllRequestGroupLineByHeaderId(@PathVariable Long headerId);

    @PostMapping("/saveRequestGroupLine/{userName}")
    ResponseEntity<ApiRequestResponse> saveRequestGroupLine(@RequestBody RequestGroupLineDto requestGroupLineDto, @PathVariable String userName);
}

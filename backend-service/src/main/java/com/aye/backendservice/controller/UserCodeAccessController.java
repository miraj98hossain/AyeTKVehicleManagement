package com.aye.backendservice.controller;

import com.aye.backendservice.service.UserCodeAccessBService;
import com.aye.commonlib.dto.request.UserCodeAccessRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-code-access")
public class UserCodeAccessController {
    @Autowired
    UserCodeAccessBService userCodeAccessBService;


    @PostMapping("/save")
    public ResponseEntity<ApiRequestResponse> save(@Valid
                                                   @RequestBody UserCodeAccessRequest userCodeAccess,
                                                   @RequestParam String currentUser) {
        return new ResponseEntity<>(userCodeAccessBService.save(userCodeAccess, currentUser), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long userCodeAccessId) {
        return new ResponseEntity<>(userCodeAccessBService.findById(userCodeAccessId), HttpStatus.OK);
    }

    @GetMapping("/findAllByUser/{id}")
    public ResponseEntity<ApiRequestResponse> findAllByUser(@PathVariable("id") Integer userId) {
        return new ResponseEntity<>(userCodeAccessBService.findAllByUser(userId), HttpStatus.OK);
    }

}

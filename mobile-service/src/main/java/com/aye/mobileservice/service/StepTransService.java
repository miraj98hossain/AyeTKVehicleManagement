package com.aye.mobileservice.service;

import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.service.MuserService;
import com.aye.commonlib.dto.request.StepTransLinesRequest;
import com.aye.commonlib.dto.request.StepTransRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.mobileservice.feignclient.StepTransServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StepTransService {
    @Autowired
    StepTransServiceFeignClient stepTransServiceFeignClient;
    @Autowired
    MuserService muserService;

    public ApiRequestResponse create(StepTransRequest stepTransRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Muser curMuser = this.muserService.findByUserName(auth.getName());
        return stepTransServiceFeignClient.create(Long.valueOf(curMuser.getId()), stepTransRequest).getBody();
    }


    public ApiRequestResponse updateLines(StepTransLinesRequest stepTransLinesRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Muser curMuser = this.muserService.findByUserName(auth.getName());
        return stepTransServiceFeignClient.updateLines(Long.valueOf(curMuser.getId()), stepTransLinesRequest).getBody();
    }


    public ApiRequestResponse findAll(Pageable pageable) {
        return stepTransServiceFeignClient.findAll(pageable).getBody();
    }


    public ApiRequestResponse findById(Long id) {
        return stepTransServiceFeignClient.findById(id).getBody();
    }


    public ApiRequestResponse findAllBySetupDtls(List<Long> setupDetailIds, String searchWords, Pageable pageable) {
        return stepTransServiceFeignClient.findAllBySetupDtls(setupDetailIds, searchWords, pageable).getBody();
    }
}

package com.aye.webservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.ScaleSetupFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 13/01/2026
 * @time: 18:10
 */
@Service
public class ScaleSetupService {
    @Autowired
    private ScaleSetupFeignClient scaleSetupFeignClient;

    public ApiRequestResponse findAllScaleSetup() {
        return scaleSetupFeignClient.findAllScaleSetup().getBody();
    }

    public ApiRequestResponse filterScaleSetup(Long orgId) {
        return scaleSetupFeignClient.filterScaleSetup(orgId).getBody();
    }

}

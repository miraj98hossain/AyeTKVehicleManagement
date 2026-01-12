package com.aye.backendservice.service.domain;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 13:46
 */


import com.aye.RestfulServer.model.Muser;
import com.aye.backendservice.entity.StepTransDetails;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;

import java.util.List;

public interface StepTransDetailsCreationStrategy {
    boolean supports(StepTransDetailsRequest req);

    List<StepTransDetails> create(StepTransDetailsRequest req, Muser user);
}


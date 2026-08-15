package com.aye.backendservice.service.domain.stepTransDetails;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 13:46
 */


import com.aye.dto.request.StepTransDetailsRequest;
import com.aye.entity.user.Muser;
import com.aye.entity.vehicleproject.StepTransDetails;

import java.util.List;

public interface StepTransDetailsCreationStrategy {
    boolean supports(StepTransDetailsRequest req);

    List<StepTransDetails> create(StepTransDetailsRequest req, Muser user);
}


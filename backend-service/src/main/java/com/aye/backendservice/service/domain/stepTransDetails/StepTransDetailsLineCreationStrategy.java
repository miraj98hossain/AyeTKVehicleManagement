package com.aye.backendservice.service.domain.stepTransDetails;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 13:46
 */


import com.aye.dto.request.StepTransDetailsLinesRequest;
import com.aye.entity.user.Muser;
import com.aye.entity.vehicleproject.StepTransDetailsLines;

import java.util.List;

public interface StepTransDetailsLineCreationStrategy {


    List<StepTransDetailsLines> create(StepTransDetailsLinesRequest req, Muser user);
}


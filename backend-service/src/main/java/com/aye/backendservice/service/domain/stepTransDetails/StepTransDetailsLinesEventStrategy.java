package com.aye.backendservice.service.domain.stepTransDetails;

import com.aye.dtoLib.dto.request.StepTransLinesRequest;
import com.aye.entitylib.entity.user.Muser;
import com.aye.entitylib.entity.vehicleproject.StepTransDetailsLines;
import com.aye.entitylib.entity.vehicleproject.StepTransTimeline;
import com.aye.enums.StepStatus;

/**
 * @author: Miraj
 * @date: 10/08/2026
 * @time: 11:20 am
 */
public interface StepTransDetailsLinesEventStrategy {
    boolean supports(StepStatus status);

    StepTransDetailsLines doEvent(Long stepTransLineId, StepTransDetailsLines stepTransDetailsLines, StepTransTimeline stepTransTimeline, StepTransLinesRequest stepTransLinesRequest, Muser user);
}

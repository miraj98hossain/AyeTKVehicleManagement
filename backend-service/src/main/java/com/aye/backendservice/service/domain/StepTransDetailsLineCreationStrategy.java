package com.aye.backendservice.service.domain;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 13:46
 */


import com.aye.commonlib.dto.request.StepTransDetailsLinesRequest;
import com.aye.entitylib.entity.Muser;
import com.aye.entitylib.entity.StepTransDetailsLines;

import java.util.List;

public interface StepTransDetailsLineCreationStrategy {


    List<StepTransDetailsLines> create(StepTransDetailsLinesRequest req, Muser user);
}


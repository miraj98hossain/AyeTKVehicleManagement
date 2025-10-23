package com.mhdev.backendservice.service;

import com.mhdev.backendservice.entity.StepTrans;

import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.StepTransResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface StepTransService {


     StepTransResponse saveStepTrans(StepTransRequest stepTransRequest);
     StepTransResponse getStepTrans(Long stepTransId);
     Page<StepTransResponse> getAllStepTrans(Pageable pageable);
}

package com.aye.backendservice.service;


import com.aye.dto.request.StepTransFilter;
import com.aye.dto.request.StepTransLineFilter;
import com.aye.dto.request.StepTransLinesRequest;
import com.aye.dto.request.StepTransRequest;
import com.aye.entity.vehicleproject.StepTrans;
import com.aye.entity.vehicleproject.StepTransLines;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface StepTransService {


    StepTrans saveStepTrans(StepTransRequest stepTransRequest, String userName);


    StepTrans updateStepTrans(StepTransRequest stepTransRequest, String userName);

    StepTrans findById(Long stepTransId);

    Page<StepTrans> findAll(Pageable pageable);

    StepTransLines updateTransLines(StepTransLinesRequest stepTransLinesRequest, String userName);

    List<Long> findAllByTempDtlId(Integer tempDtlId, Long invOrgId, Pageable pageable);

    List<StepTransLines> stepTransLinesSearch(Integer tempDtlId, StepTransLineFilter stepTransFilter);

    List<StepTrans> stepTransSearch(Integer tempDtlId, StepTransFilter stepTransFilter);
}

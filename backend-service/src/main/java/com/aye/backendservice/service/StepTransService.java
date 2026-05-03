package com.aye.backendservice.service;

import com.aye.dtoLib.dto.request.StepTransFilter;
import com.aye.dtoLib.dto.request.StepTransLinesRequest;
import com.aye.dtoLib.dto.request.StepTransRequest;
import com.aye.entitylib.entity.vehicleproject.StepTrans;
import com.aye.entitylib.entity.vehicleproject.StepTransLines;
import jdk.jshell.spi.ExecutionControl;
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

    StepTransLines updateTransLines(StepTransLinesRequest stepTransLinesRequest, String userName) throws ExecutionControl.NotImplementedException;

    List<Long> findAllByTempDtlId(Integer tempDtlId, Long invOrgId, String searchWords, Pageable pageable);

    List<StepTransLines> stepTransSearch(Integer tempDtlId, StepTransFilter stepTransFilter);
}

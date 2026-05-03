package com.aye.backendservice.service;

import com.aye.dtoLib.dto.request.StepTransDetailsLinesRequest;
import com.aye.entitylib.entity.vehicleproject.StepTransDetails;
import com.aye.entitylib.entity.vehicleproject.StepTransDetailsLines;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface StepTransDetailsService {


    StepTransDetails findById(Long stepTransDtlId);

    List<StepTransDetails> findAllByStepTransId(Long stepTransId);

    void deleteById(Long stepTransDtlId);

    void deleteAllByStepTransId(Long stepTransId);

    //***Line Section*********************
    StepTransDetailsLines saveStDtlLine(StepTransDetailsLinesRequest stepTransDetailsLinesRequest, String userName);

    StepTransDetailsLines findStDtlLineById(Long stepTransDtlLnId);

    List<StepTransDetailsLines> findAllByStTrnDtlId(Long stepTransDtlId);

    void deleteLineById(Long stepTransDtlLnId);

    List<StepTransDetails> findAllLinesByStepTransIdAndUserItemAccess(Long stepTransId);

    StepTransDetailsLines updateLine(Long stepTransLineId, Long detailLineId, String stepStatus, String userName) throws ExecutionControl.NotImplementedException;
}

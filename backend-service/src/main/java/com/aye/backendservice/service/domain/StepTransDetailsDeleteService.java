package com.aye.backendservice.service.domain;

import com.aye.backendservice.repository.StepTransDetailsLinesRepository;
import com.aye.backendservice.repository.StepTransDetailsRepository;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Miraj
 * @date: 12/01/2026
 * @time: 10:24
 */
@Service
@Transactional
public class StepTransDetailsDeleteService {
    @Autowired
    private StepTransDetailsRepository detailsRepo;
    @Autowired
    private StepTransDetailsLinesRepository linesRepo;


    public ApiRequestResponse deleteById(Long stepTransDtlId) {
        this.detailsRepo.deleteById(stepTransDtlId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully Deleted",
                null, null, null, null
        );
    }


    public void deleteAllByStepTransId(Long stepTransId) {
        this.detailsRepo.deleteAllByStepTrans_StepTransId(stepTransId);
    }


    public ApiRequestResponse deleteLineById(Long stepTransDtlLnId) {
        var line = this.linesRepo.findById(stepTransDtlLnId).orElseThrow(
                () -> new EntityNotFoundException("Step Details Lines not found with " + stepTransDtlLnId)
        );
        line.getStepTransDetails().getStepTransDetailsLines().remove(line);
        this.detailsRepo.save(line.getStepTransDetails());
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully Deleted",
                null, null, null, null
        );
    }

}

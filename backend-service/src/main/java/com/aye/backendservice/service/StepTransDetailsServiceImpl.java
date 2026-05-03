package com.aye.backendservice.service;

import com.aye.RestfulServer.service.MuserService;
import com.aye.backendservice.repository.StepTransDetailsLinesRepository;
import com.aye.backendservice.repository.StepTransDetailsRepository;
import com.aye.dtoLib.dto.request.StepTransDetailsLinesRequest;
import com.aye.dtoLib.dto.request.StepTransLinesRequest;
import com.aye.entitylib.entity.user.Muser;
import com.aye.entitylib.entity.vehicleproject.StepTransDetails;
import com.aye.entitylib.entity.vehicleproject.StepTransDetailsLines;
import com.aye.entitylib.entity.vehicleproject.StepTransLines;
import com.aye.enums.StepStatus;
import com.aye.mapper.StepTransDetailsLinesMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
public class StepTransDetailsServiceImpl implements StepTransDetailsService {
    @Autowired
    private NoGenService noGenService;
    @Autowired
    private StepTransDetailsRepository detailsRepo;
    @Autowired
    private StepTransDetailsLinesRepository lineRepo;
    @Autowired
    private StepTransDetailsLinesMapper lineMapper;
    @Autowired
    private MuserService muserService;
    @Autowired
    private StepTransService stepTransService;
    @Autowired
    private StepTransLinesService stepTransLinesService;

    @Override
    @Transactional(readOnly = true)
    public StepTransDetails findById(Long stepTransDtlId) {
        StepTransDetails stepTransDetails = detailsRepo.findById(stepTransDtlId).orElseThrow(
                () -> new EntityNotFoundException("StepTransDtl Not Found with id: " + stepTransDtlId)
        );
        return stepTransDetails;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StepTransDetails> findAllByStepTransId(Long stepTransId) {
        List<StepTransDetails> stepTransDetails = detailsRepo.findAllByStepTrans_StepTransId(stepTransId);
        return stepTransDetails;
    }

    @Transactional
    @Override
    public void deleteById(Long stepTransDtlId) {
        this.detailsRepo.deleteById(stepTransDtlId);

    }

    @Transactional
    @Override
    public void deleteAllByStepTransId(Long stepTransId) {
        this.detailsRepo.deleteAllByStepTrans_StepTransId(stepTransId);
    }

    //***Line Section*********************
    @Override
    @Transactional
    public StepTransDetailsLines saveStDtlLine(StepTransDetailsLinesRequest stepTrnsDtlLnsReq, String userName) {
        Muser muser = muserService.findByUserName(userName);
        if (stepTrnsDtlLnsReq.getStepTransDtlLnId() != null) {
            var dbTransDtl = lineRepo.findById(stepTrnsDtlLnsReq.getStepTransDtlLnId()).orElseThrow(
                    () -> new EntityNotFoundException("Step Trans Details Line Not Found with id: " + stepTrnsDtlLnsReq.getStepTransDtlLnId())
            );
            lineMapper.dtoToEntity(stepTrnsDtlLnsReq, dbTransDtl);
            dbTransDtl.setUpdatedBy(Long.valueOf(muser.getId()));
            dbTransDtl = lineRepo.save(dbTransDtl);
            return dbTransDtl;
        }
        StepTransDetailsLines stepTransDetails = lineMapper.dtoToEntity(stepTrnsDtlLnsReq);
        stepTransDetails.setCreatedBy(Long.valueOf(muser.getId()));
        stepTransDetails.setStepTransDtlLnNo(noGenService.createTransDtlLNo());
        stepTransDetails = lineRepo.save(stepTransDetails);
        return stepTransDetails;

    }

    @Override
    @Transactional(readOnly = true)
    public StepTransDetailsLines findStDtlLineById(Long stepTransDtlLnId) {
        StepTransDetailsLines stepTransDetailsLines = lineRepo.findById(stepTransDtlLnId).orElseThrow(
                () -> new EntityNotFoundException("Step Trans Details Line Not Found with id: " + stepTransDtlLnId)
        );
        return stepTransDetailsLines;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StepTransDetailsLines> findAllByStTrnDtlId(Long stepTransDtlId) {
        List<StepTransDetailsLines> stepTransDetailsLines = lineRepo
                .findAllByStepTransDetails_StepTransDtlId(stepTransDtlId);
        return stepTransDetailsLines;
    }

    @Transactional
    @Override
    public void deleteLineById(Long stepTransDtlLnId) {
        this.lineRepo.deleteById(stepTransDtlLnId);

    }

    @Transactional
    @Override
    public List<StepTransDetails> findAllLinesByStepTransIdAndUserItemAccess(Long stepTransId) {
        List<StepTransDetails> stepTransDetails = detailsRepo.findAllByStepTrans_StepTransId(stepTransId);
        return stepTransDetails;
    }

    @Transactional
    @Override
    public StepTransDetailsLines updateLine(Long stepTransLineId, Long detailLineId, String stepStatus, String userName) {
        StepTransLines stepTransLine;
        StepTransDetailsLines response = new StepTransDetailsLines();
        StepStatus status = StepStatus.valueOf(stepStatus);

        StepTransDetailsLines stepTransDetailsLines = this.findStDtlLineById(detailLineId);

        StepTransLinesRequest stepTransLinesRequest = new StepTransLinesRequest();
        stepTransLinesRequest.setStepTransLinesId(stepTransLineId);

        if (status.equals(StepStatus.W)) {
            stepTransLine = stepTransLinesService.getStepTransLine(stepTransLineId);
            List<StepTransDetails> stepTransDetails = stepTransLine.getStepTrans().getStepTransDetails();

            boolean areAllStageZero = stepTransDetails.stream()
                    .flatMap(details -> details.getStepTransDetailsLines().stream())
                    .allMatch(detailsLine -> detailsLine.getStage() == 0);
            stepTransDetailsLines.setStage(stepTransDetailsLines.getStage() + 1);
            stepTransDetailsLines.setStepStatus(StepStatus.W);
            response = this.lineRepo.save(stepTransDetailsLines);
            if (areAllStageZero) {
                stepTransLinesRequest.setStepStatus(StepStatus.W.getDisplayName());
                this.stepTransService.updateTransLines(stepTransLinesRequest, userName);
            }
        }
        if (status.equals(StepStatus.C)) {
            stepTransDetailsLines.setStage(stepTransDetailsLines.getStage() + 1);
            stepTransDetailsLines.setStepStatus(StepStatus.C);
            response = this.lineRepo.save(stepTransDetailsLines);

            stepTransLine = stepTransLinesService.getStepTransLine(stepTransLineId);

            List<StepTransDetails> stepTransDetails = stepTransLine.getStepTrans().getStepTransDetails();

            boolean isAllOtherItemsLoaded = stepTransDetails.stream()
                    .flatMap(details -> details.getStepTransDetailsLines().stream().filter(detailsLine -> !Objects.equals(detailsLine.getStepTransDtlLnId(), detailLineId)))
                    .allMatch(detailsLine -> detailsLine.getStepStatus().equals(StepStatus.C));
            if (isAllOtherItemsLoaded) {
                stepTransLinesRequest.setStepStatus(StepStatus.C.getDisplayName());
                this.stepTransService.updateTransLines(stepTransLinesRequest, userName);

            }

        }
        return response;
    }

}

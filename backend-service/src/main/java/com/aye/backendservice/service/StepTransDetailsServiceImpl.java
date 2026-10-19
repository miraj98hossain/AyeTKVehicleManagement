package com.aye.backendservice.service;


import com.aye.backendservice.aspect.annotation.EnableLogging;
import com.aye.backendservice.repository.StepTransDetailsLinesRepository;
import com.aye.backendservice.repository.StepTransDetailsRepository;
import com.aye.backendservice.service.domain.stepTransDetails.StepTransDetailsLinesEventStrategy;
import com.aye.backendservice.service.user.MuserService;
import com.aye.dto.request.StepTransDetailsLinesRequest;
import com.aye.dto.request.StepTransLinesRequest;
import com.aye.entity.user.Muser;
import com.aye.entity.vehicleproject.StepTrans;
import com.aye.entity.vehicleproject.StepTransDetails;
import com.aye.entity.vehicleproject.StepTransDetailsLines;
import com.aye.entity.vehicleproject.StepTransTimeline;
import com.aye.enums.StepStatus;
import com.aye.mapper.StepTransDetailsLinesMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static com.aye.backendservice.utils.RedisKey.STEP_TRANS_DETAIL_LINE_LOCK_KEY;


@Service
@Slf4j
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
    private List<StepTransDetailsLinesEventStrategy> strategies;
    @Autowired
    private RedisIdLockService redisIdLockService;

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
        StepTransDetailsLines line = lineRepo.findById(stepTransDtlLnId).orElseThrow(
                () -> new EntityNotFoundException("Entity Not Found with id: " + stepTransDtlLnId)
        );
        StepTransDetails stepTransDetails = this.findById(line.getStepTransDetails().getStepTransDtlId());
        stepTransDetails.getStepTransDetailsLines().remove(line);
        this.detailsRepo.save(stepTransDetails);
    }

    @Transactional
    @Override
    public List<StepTransDetails> findAllLinesByStepTransIdAndUserItemAccess(Long stepTransId) {
        List<StepTransDetails> stepTransDetails = detailsRepo.findAllByStepTrans_StepTransId(stepTransId);
        return stepTransDetails;
    }

    //    @Transactional
//    @Override
//    public StepTransDetailsLines updateLine(Long stepTransLineId, Long detailLineId, String stepStatus, String userName) throws ExecutionControl.NotImplementedException {
//        StepTransLines stepTransLine;
//        StepTransDetailsLines response = new StepTransDetailsLines();
//        StepStatus status = StepStatus.valueOf(stepStatus);
//
//        StepTransDetailsLines stepTransDetailsLines = this.findStDtlLineById(detailLineId);
//
//        StepTransLinesRequest stepTransLinesRequest = new StepTransLinesRequest();
//        stepTransLinesRequest.setStepTransLinesId(stepTransLineId);
//
//        StepTransTimeline stepTransTimeline = new StepTransTimeline();
//        stepTransTimeline.setStepTransDetailsLines(stepTransDetailsLines);
//        stepTransTimeline.setIgnitionTime(LocalDateTime.now());
//        stepTransTimeline.setStepStatus(status);
//        stepTransDetailsLines.getStepTransTimeline().add(stepTransTimeline);
//
//        if (status.equals(StepStatus.W)) {
//
//            stepTransLine = stepTransLinesService.getStepTransLine(stepTransLineId);
//            stepTransTimeline.setStepTransLines(stepTransLine);
//            List<StepTransDetails> stepTransDetails = stepTransLine.getStepTrans().getStepTransDetails();
//
//            boolean areAllStageZero = stepTransDetails.stream()
//                    .flatMap(details -> details.getStepTransDetailsLines().stream())
//                    .allMatch(detailsLine -> detailsLine.getStage() == 0);
//            stepTransDetailsLines.setStage(stepTransDetailsLines.getStage() + 1);
//            stepTransDetailsLines.setStepStatus(StepStatus.W);
//            response = this.lineRepo.save(stepTransDetailsLines);
//
//            if (areAllStageZero) {
//                stepTransLinesRequest.setStepStatus(StepStatus.W.getDisplayName());
//                this.stepTransService.updateTransLines(stepTransLinesRequest, userName);
//            }
//        }
//        if (status.equals(StepStatus.C)) {
//            stepTransDetailsLines.setStage(stepTransDetailsLines.getStage() + 1);
//            stepTransDetailsLines.setStepStatus(StepStatus.C);
//            stepTransLine = stepTransLinesService.getStepTransLine(stepTransLineId);
//            stepTransTimeline.setStepTransLines(stepTransLine);
//            response = this.lineRepo.save(stepTransDetailsLines);
//            log.info("Step Trans Details Line C event after saving" + response);
//            List<StepTransDetails> stepTransDetails = stepTransLine.getStepTrans().getStepTransDetails();
//            boolean isAllOtherItemsLoaded = stepTransDetails.stream()
//                    .flatMap(details -> details.getStepTransDetailsLines().stream().filter(detailsLine -> !Objects.equals(detailsLine.getStepTransDtlLnId(), detailLineId)))
//                    .allMatch(detailsLine -> detailsLine.getStepStatus().equals(StepStatus.C));
//            if (isAllOtherItemsLoaded) {
//                stepTransLinesRequest.setStepStatus(StepStatus.C.getDisplayName());
//                this.stepTransService.updateTransLines(stepTransLinesRequest, userName);
//
//            }
//
//        }
//        return response;
//    }
    @EnableLogging
    @Override
    @Transactional
    public StepTransDetailsLines updateLine(Long stepTransLineId, Long detailLineId, String stepStatus, String userName) {

        StepStatus status = StepStatus.valueOf(stepStatus);
        StepTransDetailsLines stepTransDetailsLines = this.findStDtlLineById(detailLineId);
        StepTransLinesRequest stepTransLinesRequest = new StepTransLinesRequest();
        stepTransLinesRequest.setStepTransLinesId(stepTransLineId);
        StepTransTimeline stepTransTimeline = new StepTransTimeline();
        stepTransTimeline.setStepTransDetailsLines(stepTransDetailsLines);
        stepTransTimeline.setIgnitionTime(LocalDateTime.now());
        stepTransTimeline.setStepStatus(status);
        Muser muser = this.muserService.findByUserName(userName);
        boolean isNotLocked = this.redisIdLockService
                .tryLock(STEP_TRANS_DETAIL_LINE_LOCK_KEY, status.getDisplayName(), detailLineId, Duration.ofHours(3).getSeconds()); // Locked for 3 Hours

        if (!isNotLocked) {
            throw new RuntimeException("Step Trans Detail Line event request for this id " + detailLineId + " is locked");
        }

        try {
            StepTransDetailsLinesEventStrategy strategy = strategies.stream()
                    .filter(s -> s.supports(status))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "Not implemented for Status: " + status.getDisplayName()));

            return strategy.doEvent(
                    stepTransLineId,
                    stepTransDetailsLines,
                    stepTransTimeline,
                    stepTransLinesRequest,
                    muser
            );

        } catch (Exception e) {
            this.redisIdLockService.unlock(STEP_TRANS_DETAIL_LINE_LOCK_KEY, status.getDisplayName(), detailLineId);
            throw e;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<StepTransDetailsLines> findByStepTransId(Long stepTransId) {
        return this.lineRepo.findAll(
                (root, query, cb) -> {
                    Join<StepTransDetailsLines, StepTransDetails> detail = root.join("stepTransDetails", JoinType.INNER);
                    Join<StepTransDetails, StepTrans> trans = detail.join("stepTrans", JoinType.INNER);

                    return cb.equal(trans.get("stepTransId"), stepTransId);
                },
                Sort.by(Sort.Direction.ASC, "invItemId")
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<StepTransDetailsLines> findByTrnRefId(Long trnRefId) {
        return this.lineRepo.findByTrnRefId(trnRefId);
    }

    @Transactional
    @Override
    public List<StepTransDetailsLines> saveAllLines(List<StepTransDetailsLines> stepTransDetailsLines) {
        return this.lineRepo.saveAll(stepTransDetailsLines);
    }
}

package com.aye.backendservice.service;

import com.aye.RestfulServer.service.MuserService;
import com.aye.backendservice.repository.StepTransScaleDetailsRepository;
import com.aye.dtoLib.dto.response.StepTransScaleDetailsDto;
import com.aye.entitylib.entity.user.Muser;
import com.aye.entitylib.entity.vehicleproject.StepTransScaleDetails;
import com.aye.entitylib.entity.vehicleproject.embeddable.StepTransScaleDetailsId;
import com.aye.mapper.StepTransScaleDetailsMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * @author: Miraj
 * @date: 06/05/2026
 * @time: 4:44 pm
 */
@Service
public class StepTransScaleDetailsServiceImpl implements StepTransScaleDetailsService {
    @Autowired
    private StepTransScaleDetailsRepository repository;
    @Autowired
    private StepTransScaleDetailsMapper mapper;
    @Autowired
    private MuserService muserService;


    @Override
    @Transactional
    public StepTransScaleDetails save(StepTransScaleDetailsDto request, String currentUser) {
        Muser muser = muserService.findByUserName(currentUser);
        StepTransScaleDetailsId id = new StepTransScaleDetailsId();
        id.setStepTransId(request.getStepTransId());
        id.setStepTransLinesId(request.getStepTransLineId());
        id.setStepSetupDetailsId(request.getStepSetupDetailId());
        Optional<StepTransScaleDetails> dbEntity = this.repository.findById(id);
        if (dbEntity.isPresent()) {
            mapper.toEntity(request, dbEntity.get());
            dbEntity.get().setUpdatedBy(Long.valueOf(muser.getId()));
            return repository.save(dbEntity.get());
        } else {
            StepTransScaleDetails stepTransScaleDetails = mapper.toEntity(request);
            stepTransScaleDetails.setCreatedAt(new Date());
            stepTransScaleDetails.setCreatedBy(Long.valueOf(muser.getId()));
            return repository.save(stepTransScaleDetails);
        }

    }

    @Override
    @Transactional
    public StepTransScaleDetails findById(StepTransScaleDetailsId stepTransScaleDetailsId) {
        return repository.findById(stepTransScaleDetailsId).orElseThrow(
                () -> new EntityNotFoundException("Step Trans's Scale Details not found with id: " + stepTransScaleDetailsId)
        );
    }

}

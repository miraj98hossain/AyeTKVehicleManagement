package com.aye.backendservice.service;


import com.aye.backendservice.repository.StepTransTripDetailsRepository;
import com.aye.backendservice.service.user.MuserService;
import com.aye.dto.response.StepTransTripDetailsDto;
import com.aye.entity.user.Muser;
import com.aye.entity.vehicleproject.StepTransTripDetails;
import com.aye.entity.vehicleproject.embeddable.StepTransTripDetailsId;
import com.aye.mapper.StepTransTripDetailsMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author: Miraj
 * @date: 06/05/2026
 * @time: 4:44 pm
 */
@Service
public class StepTransTripDetailsServiceImpl implements StepTransTripDetailsService {
    @Autowired
    private StepTransTripDetailsRepository repository;
    @Autowired
    private StepTransTripDetailsMapper mapper;
    @Autowired
    private MuserService muserService;


    @Override
    @Transactional
    public StepTransTripDetails save(StepTransTripDetailsDto request, String currentUser) {
        Muser muser = muserService.findByUserName(currentUser);
        StepTransTripDetailsId id = new StepTransTripDetailsId();
        id.setStepTransId(request.getStepTransId());
        id.setStepTransLinesId(request.getStepTransLineId());
        Optional<StepTransTripDetails> dbEntity = this.repository.findById(id);
        if (dbEntity.isPresent()) {
            mapper.toEntity(request, dbEntity.get());
            dbEntity.get().setUpdatedBy(Long.valueOf(muser.getId()));
            return repository.save(dbEntity.get());
        } else {
            StepTransTripDetails stepTransTripDetails = mapper.toEntity(request);
            //StepTransTripDetails.setCreatedAt(new Date());
            stepTransTripDetails.setCreatedBy(Long.valueOf(muser.getId()));
            return repository.save(stepTransTripDetails);
        }

    }

    @Override
    @Transactional
    public StepTransTripDetails findById(StepTransTripDetailsId stepTransTripDetailsId) {
        return repository.findById(stepTransTripDetailsId).orElseThrow(
                () -> new EntityNotFoundException("Step Trans's Trip Details not found with id: " + stepTransTripDetailsId)
        );
    }

}

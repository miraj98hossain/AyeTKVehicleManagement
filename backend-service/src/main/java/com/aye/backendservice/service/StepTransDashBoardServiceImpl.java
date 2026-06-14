package com.aye.backendservice.service;

import com.aye.backendservice.repository.StepTransTotalTimeVRepository;
import com.aye.backendservice.repository.StepTransactionVolumeVRepository;
import com.aye.backendservice.repository.StepWiseVehicleSummaryVRepository;
import com.aye.backendservice.repository.StrStepWiseTotalTimeVRepository;
import com.aye.entitylib.entity.vehicleproject.StepTransTotalTimeV;
import com.aye.entitylib.entity.vehicleproject.StepTransactionVolumeV;
import com.aye.entitylib.entity.vehicleproject.StepWiseVehicleSummaryV;
import com.aye.entitylib.entity.vehicleproject.StrStepWiseTotalTimeV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Miraj
 * @date: 06/06/2026
 * @time: 2:10 pm
 */
@Service
public class StepTransDashBoardServiceImpl implements StepTransDashBoardService {
    @Autowired
    private StepWiseVehicleSummaryVRepository stepWiseVehicleSummaryVRepository;

    @Autowired
    private StepTransactionVolumeVRepository stepTransactionVolumeVRepository;

    @Autowired
    private StepTransTotalTimeVRepository stepTransTotalTImeVRepository;

    @Autowired
    private StrStepWiseTotalTimeVRepository strStepWiseTotalTImeVRepository;

    @Override
    public List<StepWiseVehicleSummaryV> getStepWiseVehicleSummary() {
        return this.stepWiseVehicleSummaryVRepository.findAll();
    }

    @Override
    public List<StepTransactionVolumeV> getStepTransactionVolume() {
        return this.stepTransactionVolumeVRepository.findAll();
    }

    @Override
    public List<StepTransTotalTimeV> getStepTransTotalTime() {
        return this.stepTransTotalTImeVRepository.findAll();
    }

    @Override
    public List<StrStepWiseTotalTimeV> getStrStepWiseTotalTimeByTransId(Long stepTransId) {
        return this.strStepWiseTotalTImeVRepository.findAllByStepTransId(stepTransId);
    }


}

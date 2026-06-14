package com.aye.backendservice.service;

import com.aye.entitylib.entity.vehicleproject.StepTransTotalTimeV;
import com.aye.entitylib.entity.vehicleproject.StepTransactionVolumeV;
import com.aye.entitylib.entity.vehicleproject.StepWiseVehicleSummaryV;
import com.aye.entitylib.entity.vehicleproject.StrStepWiseTotalTimeV;

import java.util.List;

/**
 * @author: Miraj
 * @date: 06/06/2026
 * @time: 2:10 pm
 */
public interface StepTransDashBoardService {
    List<StepWiseVehicleSummaryV> getStepWiseVehicleSummary();

    List<StepTransactionVolumeV> getStepTransactionVolume();

    List<StepTransTotalTimeV> getStepTransTotalTime();

    List<StrStepWiseTotalTimeV> getStrStepWiseTotalTimeByTransId(Long stepTransId);
}

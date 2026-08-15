package com.aye.backendservice.service;

import com.aye.entity.vehicleproject.StepWiseTransCountV;

import java.util.List;

public interface StepWiseTransCountVService {
    List<StepWiseTransCountV> getCountByDetailId(List<Long> detailId);
}

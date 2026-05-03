package com.aye.backendservice.service;

import com.aye.entitylib.entity.vehicleproject.ScaleSetup;

import java.util.List;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 16:04
 */
public interface ScaleSetupService {
    List<ScaleSetup> findAllScaleSetup();

    List<ScaleSetup> filterScaleSetup(Long orgId);
}

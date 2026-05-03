package com.aye.backendservice.service;


import com.aye.backendservice.repository.ScaleSetupRepo;
import com.aye.entitylib.entity.vehicleproject.ScaleSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 16:04
 */
@Service
public class ScaleSetupServiceImpl implements ScaleSetupService {
    @Autowired
    private ScaleSetupRepo scaleSetupRepo;


    @Override
    public List<ScaleSetup> findAllScaleSetup() {
        var list = scaleSetupRepo.findAll();
        return list;
    }

    @Override
    public List<ScaleSetup> filterScaleSetup(Long orgId) {
        var list = scaleSetupRepo.findAllByOrgHierarchy_Id(orgId);
        return list;
    }
}

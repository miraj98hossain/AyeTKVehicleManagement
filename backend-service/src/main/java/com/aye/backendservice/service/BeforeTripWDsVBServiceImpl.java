package com.aye.backendservice.service;

import com.aye.RestfulServer.service.BeforeTripWDsVService;
import com.aye.backendservice.mapper.BeforeTripWDsVMapper;
import com.aye.commonlib.dto.response.BeforeTripWDsVResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 10:43
 * @project: AyeTKVehicleManagement
 */
@Service
public class BeforeTripWDsVBServiceImpl implements BeforeTripWDsVBService {
    @Autowired
    private BeforeTripWDsVService beforeTripWDsVService;
    @Autowired
    private BeforeTripWDsVMapper beforeTripWDsVMapper;

    @Override
    public List<BeforeTripWDsVResponse> findScheduleId(Long orgId, Long invOrgId) {
        return beforeTripWDsVService.findScheduleId(orgId, invOrgId).stream()
                .map(beforeTripWDsVMapper::toResponseDto).toList();
    }
}

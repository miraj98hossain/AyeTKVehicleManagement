package com.aye.backendservice.service;

import com.aye.RestfulServer.service.BeforeTripVService;
import com.aye.backendservice.mapper.BeforeTripVMapper;
import com.aye.commonlib.dto.response.BeforeTripVResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 12:39
 * @project: AyeTKVehicleManagement
 */
@Service
public class BeforeTripVBServiceImpl implements BeforeTripVBService {
    @Autowired
    private BeforeTripVService beforeTripVService;
    @Autowired
    private BeforeTripVMapper beforeTripVMapper;

    @Override
    public List<BeforeTripVResponse> findOnHandOrders(Long orgId, Long invOrgId) {
        
        return this.beforeTripVService.findOnHandOrders(orgId, invOrgId, "On Hand").stream().map(
                beforeTripVMapper::toResponseDto
        ).toList();
    }
}

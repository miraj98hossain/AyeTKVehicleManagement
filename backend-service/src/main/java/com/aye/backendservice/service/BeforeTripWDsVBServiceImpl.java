package com.aye.backendservice.service;

import com.aye.RestfulServer.service.BeforeTripWDsVService;
import com.aye.backendservice.mapper.BeforeTripWDsVMapper;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.BeforeTripWDsVResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    public ApiRequestResponse findScheduleId(Long orgId, Long invOrgId, String searchWords) {

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "dsList",
                BeforeTripWDsVResponse.class.getName(),
                this.beforeTripWDsVService.findScheduleId(orgId, invOrgId, searchWords).stream()
                        .map(beforeTripWDsVMapper::toResponseDto).toList()
        );
    }
}

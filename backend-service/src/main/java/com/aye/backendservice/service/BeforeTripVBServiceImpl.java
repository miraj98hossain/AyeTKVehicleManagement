package com.aye.backendservice.service;

import com.aye.RestfulServer.service.BeforeTripVService;
import com.aye.backendservice.mapper.BeforeTripVMapper;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.BeforeTripVResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    public ApiRequestResponse getDeliveryNumbers(Long orgId, Long invOrgId, Long searchWords) {

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "doList",
                BeforeTripVResponse.class.getName(),
                this.beforeTripVService.getDeliveryNumbers(orgId, invOrgId, "On Hand", searchWords).stream().map(
                        beforeTripVMapper::toResponseDto
                ).toList()
        );
    }
}

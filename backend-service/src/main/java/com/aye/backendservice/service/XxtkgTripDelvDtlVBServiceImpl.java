package com.aye.backendservice.service;


import com.aye.backendservice.service.orgManagement.XxtkgTripDelvDtlVService;
import com.aye.dto.response.ApiRequestResponse;

import com.aye.dto.response.ApiRequestResponseDetail;
import com.aye.dto.response.XxtkgTripDelvDtlVResponse;
import com.aye.mapper.schedule.XxtkgTripDelvDtlVMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Miraj
 * @date: 08/01/2026
 * @time: 11:52
 */
@Service
public class XxtkgTripDelvDtlVBServiceImpl implements XxtkgTripDelvDtlVBService {

    @Autowired
    private XxtkgTripDelvDtlVService xxtkgTripDelvDtlVService;
    @Autowired
    private XxtkgTripDelvDtlVMapper xxtkgTripDelvDtlVMapper;

    @Transactional(readOnly = true)
    @Override
    public ApiRequestResponse filterChallanNumber(Long orgId, Long invOrgId, String searchWords) {
        var list = xxtkgTripDelvDtlVService.filterChallanNumber(orgId, invOrgId, searchWords)
                .stream().map(xxtkgTripDelvDtlVMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "tripDelvDtlVResList",
                XxtkgTripDelvDtlVResponse.class.getName(),
                list
        );
    }
}

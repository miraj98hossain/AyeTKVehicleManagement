package com.aye.backendservice.service;

import com.aye.RestfulServer.service.OrderedCustomerVService;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;
import com.aye.dtoLib.dto.response.order.OrderedCustomerVDto;
import com.aye.mapper.order.OrderedCustomerVMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 10:02
 */
@Service
public class OrderedCustomerVBServiceImpl implements OrderedCustomerVBService {
    @Autowired
    private OrderedCustomerVService orderedCustomerVBService;
    @Autowired
    private OrderedCustomerVMapper orderedCustomerVMapper;

    @Override
    public ApiRequestResponse filterCustomer(Long orgId, String searchWords) {
        var list = this.orderedCustomerVBService.filterCustomer(orgId, searchWords);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "custList",
                OrderedCustomerVDto.class.getName(),
                list.stream().map(orderedCustomerVMapper::toResponseDto)
        );
    }
}

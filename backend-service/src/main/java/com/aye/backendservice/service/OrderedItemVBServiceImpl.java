package com.aye.backendservice.service;

import com.aye.RestfulServer.service.OrderedItemVService;

import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;

import com.aye.dtoLib.dto.response.order.OrderedItemVResDto;
import com.aye.mapper.order.OrderedItemVMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 04/01/2026
 * @time: 17:27
 */
@Service
public class OrderedItemVBServiceImpl implements OrderedItemVBService {
    @Autowired
    private OrderedItemVService orderedItemVService;
    @Autowired
    private OrderedItemVMapper orderedItemVMapper;

    @Override
    public ApiRequestResponse filterOrderedItem(Long orgId, Long invOrgId, String searchWords) {
        var list = orderedItemVService.filterOrderedItem(orgId, invOrgId, searchWords);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "orderedItems",
                OrderedItemVResDto.class.getName(),
                list.stream().map(orderedItemVMapper::toResponseDto).toList()
        );
    }
}

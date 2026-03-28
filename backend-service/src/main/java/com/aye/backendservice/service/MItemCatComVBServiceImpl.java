package com.aye.backendservice.service;

import com.aye.RestfulServer.service.MItemCatComVService;

import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;
import com.aye.dtoLib.dto.response.MItemCatComVResponse;
import com.aye.mapper.order.MItemCatComVMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MItemCatComVBServiceImpl implements MItemCatComVBService {
    @Autowired
    private MItemCatComVService mItemCatComVService;
    @Autowired
    private MItemCatComVMapper mItemCatComVMapper;

    @Override
    public ApiRequestResponse getAllItemCatComb() {
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "itemCatComb",
                MItemCatComVResponse.class.getName(),
                mItemCatComVService.getAllItemCatComb().stream().map(mItemCatComVMapper::toResponseDto).toList()
        );
    }

    @Override
    public ApiRequestResponse filterItemCatComb(Long orgId, String searchWords) {
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "itemCatComb",
                MItemCatComVResponse.class.getName(),
                mItemCatComVService.filterItemCatComb(orgId, searchWords)
                        .stream()
                        .map(mItemCatComVMapper::toResponseDto).toList()
        );
    }
}

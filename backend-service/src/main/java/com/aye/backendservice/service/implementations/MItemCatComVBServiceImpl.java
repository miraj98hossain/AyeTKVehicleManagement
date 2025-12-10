package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.service.MItemCatComVService;
import com.aye.backendservice.mapper.MItemCatComVMapper;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.MItemCatComVBService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.MItemCatComVResponse;
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

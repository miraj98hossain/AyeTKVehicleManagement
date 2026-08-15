package com.aye.backendservice.service;


import com.aye.backendservice.service.userData.MuserDataItemService;
import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.ApiRequestResponseDetail;
import com.aye.entity.user.userData.MuserDataItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuserDataItemBServiceImpl implements MuserDataItemBService {
    @Autowired
    MuserDataItemService muserDataItemService;

    @Override
    public ApiRequestResponse findByOrgId(Long orgId) {
        List<MuserDataItem.ItemInfo> itemInfoList = this.muserDataItemService.findByOrgId(orgId);

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "muserDataCustList",
                MuserDataItem.ItemInfo.class.getName(), itemInfoList
        );
    }
}

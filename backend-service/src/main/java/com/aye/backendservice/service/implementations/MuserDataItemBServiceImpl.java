package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.model.userData.MuserDataItem;
import com.aye.RestfulServer.service.MuserDataItemService;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.MuserDataItemBService;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
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

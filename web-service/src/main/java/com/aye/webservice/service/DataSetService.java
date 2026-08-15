package com.aye.webservice.service;

import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.concurrency.DataSetDto;
import com.aye.webservice.feignclient.DataSetFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 12/08/2026
 * @time: 9:35 am
 */
@Service
public class DataSetService {
    @Autowired
    private DataSetFeignClient dataSetFeignClient;


    public ApiRequestResponse getAllDataSet() {
        return this.dataSetFeignClient.getAllDataSet().getBody();
    }


    public ApiRequestResponse saveDataSet(DataSetDto dataSetMDT) {
        return this.dataSetFeignClient.saveDataSet(dataSetMDT).getBody();
    }


    public ApiRequestResponse getDataSetById(Long dataSetId) {
        return this.dataSetFeignClient.getDataSetById(dataSetId).getBody();
    }

}

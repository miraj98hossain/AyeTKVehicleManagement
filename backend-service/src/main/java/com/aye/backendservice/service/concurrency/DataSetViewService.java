package com.aye.backendservice.service.concurrency;

import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.dto.response.ApiRequestResponse;
import com.aye.dto.response.ApiRequestResponseDetail;
import com.aye.dto.response.concurrency.DataSetDto;
import com.aye.mapper.concurrency.DataSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Miraj
 * @date: 11/08/2026
 * @time: 4:52 pm
 */
@Service
public class DataSetViewService {

    @Autowired
    private DataSetService dataSetService;
    @Autowired
    private DataSetMapper dataSetMapper;

    public ApiRequestResponse findAll() {
        List<DataSetDto> dataSets = dataSetMapper.toResponseDto(dataSetService.findAll());
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "dataSetList",
                DataSetDto.class.getName(), dataSets
        );
    }


    public ApiRequestResponse findById(Long id) {
        DataSetDto dataSetDto = this.dataSetMapper.toResponseDto(dataSetService.findById(id));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "dataSet",
                DataSetDto.class.getName(), dataSetDto
        );
    }


    public ApiRequestResponse save(DataSetDto dataSet) {
        DataSetDto dataSetDto = this.dataSetMapper.toResponseDto(dataSetService.save(dataSet));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "dataSet",
                DataSetDto.class.getName(), dataSetDto
        );
    }

}

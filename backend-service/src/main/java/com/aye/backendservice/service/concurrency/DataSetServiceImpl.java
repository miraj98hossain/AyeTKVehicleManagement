package com.aye.backendservice.service.concurrency;


import com.aye.backendservice.repository.concurrency.DataSetRepo;
import com.aye.dto.response.concurrency.DataSetDto;
import com.aye.entity.concurrency.DataSet;
import com.aye.mapper.concurrency.DataSetMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Toufiq on 2/10/2019.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DataSetServiceImpl implements DataSetService {

    private final DataSetRepo dataSetRepo;
    private final DataSetMapper dataSetMapper;

    @Override
    public List<DataSet> findAll() {
        return dataSetRepo.findAll();
    }

    @Override
    public DataSet findById(Long id) {
        return dataSetRepo.findById(id).orElseThrow(
                () -> new RuntimeException("DataSet not found with id " + id)
        );
    }

    @Override
    public DataSet save(DataSetDto dataSet) {
        DataSet dataSetEntity;
        if (dataSet.getId() == null) {
            dataSetEntity = this.dataSetMapper.toEntity(dataSet);
        } else {
            dataSetEntity = this.findById(dataSet.getId());
            this.dataSetMapper.toEntity(dataSet, dataSetEntity);
        }
        return dataSetRepo.save(dataSetEntity);
    }

}
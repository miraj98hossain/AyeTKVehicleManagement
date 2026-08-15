package com.aye.backendservice.service.concurrency;

import com.aye.dto.response.concurrency.DataSetDto;
import com.aye.entity.concurrency.DataSet;

import java.util.List;

/**
 * @author: Miraj
 * @date: 11/08/2026
 * @time: 4:50 pm
 */
public interface DataSetService {
    List<DataSet> findAll();

    DataSet findById(Long id);

    DataSet save(DataSetDto dataSet);
}

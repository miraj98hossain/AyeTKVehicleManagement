package com.aye.backendservice.repository.concurrency;

import com.aye.entity.concurrency.DataSet;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface DataSetRepo extends JpaRepository<DataSet, Long> {
}

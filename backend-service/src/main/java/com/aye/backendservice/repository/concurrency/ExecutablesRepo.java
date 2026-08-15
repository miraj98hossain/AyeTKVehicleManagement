package com.aye.backendservice.repository.concurrency;


import com.aye.entity.concurrency.Executables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Toufiq on 3/9/2019.
 */
public interface ExecutablesRepo extends JpaRepository<Executables, Long> {

    List<Executables> findByNameIgnoreCaseContaining(String name);

    Executables findByFileName(String fileName);

    List<Executables> findByappModuleId(long appModule);


}

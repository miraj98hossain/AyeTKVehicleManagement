package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.AppModule;
import com.aye.enums.AppModuleCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppModuleRepo extends JpaRepository<AppModule, Long> {

    List<AppModule> findAll();

    AppModule findByModuleCode(AppModuleCode code);

}

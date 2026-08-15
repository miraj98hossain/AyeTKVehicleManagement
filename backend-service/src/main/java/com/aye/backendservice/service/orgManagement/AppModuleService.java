package com.aye.backendservice.service.orgManagement;


import com.aye.entity.AppModule;
import com.aye.enums.AppModuleCode;

import java.util.List;

public interface AppModuleService {

    List<AppModule> findall();

    AppModule findById(Long id);

    List<AppModule> findByIds(List<Long> ids);

    AppModule findByCode(AppModuleCode code);

    void saveAppmodule(AppModule appModule);

}

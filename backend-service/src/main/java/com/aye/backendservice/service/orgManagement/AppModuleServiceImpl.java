package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.orgManagement.AppModuleRepo;
import com.aye.entity.AppModule;
import com.aye.enums.AppModuleCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AppModuleServiceImpl implements AppModuleService {

    @Autowired
    private AppModuleRepo appModuleRepo;

    @Override
    public List<AppModule> findall() {
        // TODO Auto-generated method stub
        return this.appModuleRepo.findAll();
    }

    @Override
    public AppModule findById(Long id) {
//		return this.appModuleRepo.getOne(id);
        return this.appModuleRepo.findById(id).orElse(null);
    }

    @Override
    public List<AppModule> findByIds(List<Long> ids) {
        return this.appModuleRepo.findAllById(ids);
    }

    @Override
    public AppModule findByCode(AppModuleCode code) {
        return this.appModuleRepo.findByModuleCode(code);
    }

    @Override
    public void saveAppmodule(AppModule appModule) {
        this.appModuleRepo.save(appModule);
    }

}

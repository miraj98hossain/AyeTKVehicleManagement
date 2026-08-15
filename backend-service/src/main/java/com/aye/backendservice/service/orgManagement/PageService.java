package com.aye.backendservice.service.orgManagement;


import com.aye.entity.Pages;

import java.util.List;

/**
 * Created by Toufiq on 11/2/2019.
 */
public interface PageService {
    List<Pages> findAll();

    Pages findById(Long id);

    Pages findByName(String name);

    void save(Pages p);

    void delete(Pages p);

    public Pages findByUrl(String p);

    List<Pages> findByPageGroup(Integer group);
}

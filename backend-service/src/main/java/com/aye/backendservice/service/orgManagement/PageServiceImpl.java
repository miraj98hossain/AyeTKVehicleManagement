package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.orgManagement.PageRepo;
import com.aye.entity.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Toufiq on 11/2/2019.
 */
@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private PageRepo pageRepo;


    @Override
    public List<Pages> findAll() {
        return this.pageRepo.findAllByOrderByPageNameAsc();
    }

    @Override
    public Pages findById(Long id) {
        return this.pageRepo.getOne(id);
    }

    @Override
    public Pages findByName(String name) {
        return this.pageRepo.findByPageName(name);
    }

    @Override
    public void save(Pages p) {
        this.pageRepo.save(p);
    }

    @Override
    public void delete(Pages p) {
        this.pageRepo.delete(p);
    }

    @Override
    public Pages findByUrl(String p) {
        Pages pages = new Pages();
//        for(PageUrl q : PageUrl.values())
//        {
//
//            if (q.getDisplayName().equals(p))
//            {
//                pages= this.pageRepo.findByPageUrl(q);
////                System.out.println(q.getDisplayName());
//            }
//        }
        return pages;
    }

    @Override
    public List<Pages> findByPageGroup(Integer group) {
//        return this.pageRepo.findByPageGroup(group);
        return null;
    }
}

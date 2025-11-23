package com.aye.webservice.service;

import com.aye.commonlib.dto.request.PagesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagesService {
    @Autowired
    private PagesService pagesService;

    ApiRequestResponse findAll() {
        return this.pagesService.findAll();
    }

    ApiRequestResponse findById(Long id) {
        return this.pagesService.findById(id);
    }

    ApiRequestResponse findByName(String name) {
        return this.pagesService.findByName(name);
    }

    ApiRequestResponse save(PagesRequest pagesRequest) {
        return this.pagesService.save(pagesRequest);
    }

    ApiRequestResponse delete(Long id) {
        return this.pagesService.delete(id);
    }

    ApiRequestResponse findByUrl(String p) {
        return this.pagesService.findByUrl(p);
    }

    ApiRequestResponse findByPageGroup(Integer group) {
        return this.pagesService.findByPageGroup(group);
    }
}

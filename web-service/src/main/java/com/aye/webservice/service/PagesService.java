package com.aye.webservice.service;

import com.aye.commonlib.dto.request.PagesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.PagesFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagesService {
    @Autowired
    private PagesFeignClient pagesService;

    ApiRequestResponse findAll() {
        return this.pagesService.findAll().getBody();
    }

    ApiRequestResponse findById(Long id) {
        return this.pagesService.findById(id).getBody();
    }

    ApiRequestResponse findByName(String name) {
        return this.pagesService.findByName(name).getBody();
    }

    ApiRequestResponse save(PagesRequest pagesRequest) {
        return this.pagesService.save(pagesRequest).getBody();
    }

    ApiRequestResponse delete(Long id) {
        return this.pagesService.delete(id).getBody();
    }

    ApiRequestResponse findByUrl(String p) {
        return this.pagesService.findByUrl(p).getBody();
    }

    ApiRequestResponse findByPageGroup(Integer group) {
        return this.pagesService.findByPageGroup(group).getBody();
    }
}

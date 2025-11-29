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

    public ApiRequestResponse findAll() {
        return this.pagesService.findAll().getBody();
    }

    public ApiRequestResponse findById(Long id) {
        return this.pagesService.findById(id).getBody();
    }

    public ApiRequestResponse findByName(String name) {
        return this.pagesService.findByName(name).getBody();
    }

    public ApiRequestResponse save(PagesRequest pagesRequest) {
        return this.pagesService.save(pagesRequest).getBody();
    }

    public ApiRequestResponse delete(Long id) {
        return this.pagesService.delete(id).getBody();
    }

    public ApiRequestResponse findByUrl(String p) {
        return this.pagesService.findByUrl(p).getBody();
    }

    public ApiRequestResponse findByPageGroup(Integer group) {
        return this.pagesService.findByPageGroup(group).getBody();
    }
}

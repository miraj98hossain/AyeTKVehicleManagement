package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.PagesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface PagesBService {
    ApiRequestResponse findAll();

    ApiRequestResponse findById(Long id);

    ApiRequestResponse findByName(String name);

    ApiRequestResponse save(PagesRequest pagesRequest);

    ApiRequestResponse delete(Long id);

    ApiRequestResponse findByUrl(String p);

    ApiRequestResponse findByPageGroup(Integer group);
}

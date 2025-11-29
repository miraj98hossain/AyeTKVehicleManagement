package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.model.Pages;
import com.aye.RestfulServer.service.PageService;
import com.aye.backendservice.mapper.PagesMapper;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.PagesBService;
import com.aye.commonlib.dto.request.PagesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.PagesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagesBServiceImpl implements PagesBService {
    @Autowired
    private PageService pagesService;
    @Autowired()
    private PagesMapper pagesMapper;

    @Override
    public ApiRequestResponse findAll() {
        List<PagesResponse> pagesResponse = pagesService.findAll().stream()
                .map(pagesMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "pagesList",
                PagesResponse.class.getName(), pagesResponse
        );
    }

    @Override
    public ApiRequestResponse findById(Long id) {
        PagesResponse pagesResponse = this.pagesMapper
                .toResponseDto(pagesService.findById(id));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "page",
                PagesResponse.class.getName(), pagesResponse
        );
    }

    @Override
    public ApiRequestResponse findByName(String name) {
        PagesResponse pagesResponse = this.pagesMapper
                .toResponseDto(pagesService.findByName(name));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "page",
                PagesResponse.class.getName(), pagesResponse
        );
    }

    @Override
    public ApiRequestResponse save(PagesRequest pagesRequest) {
        Pages pages = this.pagesMapper.dtoToEntity(pagesRequest);
        this.pagesService.save(pages);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                null, null,
                null, null
        );
    }

    @Override
    public ApiRequestResponse delete(Long id) {
        Pages pages = this.pagesService.findById(id);
        this.pagesService.delete(pages);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                null, null,
                null, null
        );
    }

    @Override
    public ApiRequestResponse findByUrl(String p) {
        PagesResponse pagesResponse = this.pagesMapper.toResponseDto(pagesService.findByUrl(p));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "page",
                PagesResponse.class.getName(), pagesResponse
        );
    }

    @Override
    public ApiRequestResponse findByPageGroup(Integer group) {
        List<PagesResponse> pagesResponse = pagesService.findByPageGroup(group)
                .stream().map(pagesMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "page",
                PagesResponse.class.getName(), pagesResponse
        );
    }
}

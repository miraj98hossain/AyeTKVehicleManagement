package com.aye.backendservice.utils;

/**
 * @author: Miraj
 * @date: 11/01/2026
 * @time: 13:51
 */


import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import org.springframework.http.HttpStatus;

public class ApiResponseFactory {

    public static <T> ApiRequestResponse success(T data) {
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "data",
                data.getClass().getName(),
                data
        );
    }
}


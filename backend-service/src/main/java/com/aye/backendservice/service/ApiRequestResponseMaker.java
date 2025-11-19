package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;


public class ApiRequestResponseMaker {

    public static ApiRequestResponse make(
            String httpStatus,
            String message,
            ApiRequestResponseDetail.ObjectType objectType,
            String objectTag,
            String mapperClass,
            Object object
    ) {
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(httpStatus);
        response.setMessage(message);
        ApiRequestResponseDetail responseDetail = ApiRequestResponseDetail.builder()
                .mapperClass(mapperClass)
                .objectType(objectType)
                .object(object)
                .objectTag(objectTag)
                .build();
        response.getApiRequestResponseDetails().add(responseDetail);
        return response;
    }
}

package com.mhdev.backendservice.mapper;

import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import com.mhdev.commonlib.dto.response.ApiRequestResponseDetail;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface ApiRequestResponseMapper {
    @Mapping(source = "httpStatus", target = "httpStatus")
    @Mapping(source = "message", target = "message")
    @Mapping(target = "apiRequestResponseDetails",
            expression = "java(mapObjectsToDetails(objectTag, objectType, object, mapperClass))")
    ApiRequestResponse toApiRequestResponseMapper(
            String httpStatus,
            String message,
            String objectTag,
            ApiRequestResponseDetail.ObjectType objectType,
            List<Object> object,
            Class<?> mapperClass);


    default List<ApiRequestResponseDetail> mapObjectsToDetails(
            String objectTag,
            ApiRequestResponseDetail.ObjectType objectType,
            List<Object> objectList,
            Class<?> mapperClass) {

        if (objectList == null) return new ArrayList<>();

        List<ApiRequestResponseDetail> details = new ArrayList<>();
        for (Object obj : objectList) {
            ApiRequestResponseDetail detail = ApiRequestResponseDetail.builder()
                    .object(obj)
                    .objectType(objectType)
                    .objectTag(objectTag)
                    .mapperClass(mapperClass.getName())
                    .build();
            details.add(detail);
        }
        return details;
    }

}

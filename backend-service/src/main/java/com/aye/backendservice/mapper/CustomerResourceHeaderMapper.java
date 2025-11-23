//package com.aye.backendservice.mapper;
//
//import com.aye.RestfulServer.model.user.CustomerResourceHeader;
//import com.aye.commonlib.dto.request.CustomerResourceHeaderRequest;
//import com.aye.commonlib.dto.response.CustomerResourceHeaderResponse;
//import org.mapstruct.*;
//
//@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
//        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
//        builder = @Builder(disableBuilder = true))
//public interface CustomerResourceHeaderMapper {
//
//    CustomerResourceHeaderResponse toResponseDto(CustomerResourceHeader source);
//
//    @Mapping(target = "resourceStatus", expression = "java(stringToEnum(request.getResourceStatus()))")
//    @Mapping(target = "createdBy", ignore = true)
//    @Mapping(target = "lastUpdateBy", ignore = true)
//    @Mapping(target = "creationDate", ignore = true)
//    @Mapping(target = "lastUpdateDate", ignore = true)
//    @Mapping(target = "lastUpdateLogin", ignore = true)
//    @Mapping(target = "customerResourceLines", ignore = true)
//    CustomerResourceHeader dtoToEntity(CustomerResourceHeaderRequest request);
//
//
//}

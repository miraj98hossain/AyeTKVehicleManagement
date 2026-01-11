package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.om.XxtkgTripDelvDtlV;
import com.aye.commonlib.dto.response.XxtkgTripDelvDtlVResponse;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @author: Miraj
 * @date: 08/01/2026
 * @time: 12:17
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface XxtkgTripDelvDtlVMapper {

    XxtkgTripDelvDtlVResponse toResponseDto(XxtkgTripDelvDtlV xxtkgTripDelvDtlV);
}

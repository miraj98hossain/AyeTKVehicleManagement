package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.userData.MuserDataCust;
import com.aye.commonlib.dto.response.MuserDataCustResponse;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface MuserDataCustMapper {
    MuserDataCustResponse toResponseDto(MuserDataCust muserDataCust);
}

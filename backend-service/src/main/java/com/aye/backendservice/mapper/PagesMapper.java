package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.Pages;
import com.aye.commonlib.dto.response.PagesResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface PagesMapper {
    @Mapping(source = "pageUrl.displayName", target = "pageUrlDisplayName")
    PagesResponse toResponseDto(Pages pages);
}

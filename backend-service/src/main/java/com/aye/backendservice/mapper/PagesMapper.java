package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.PageUrl;
import com.aye.RestfulServer.model.Pages;
import com.aye.RestfulServer.model.RoleTypes;
import com.aye.commonlib.dto.request.PagesRequest;
import com.aye.commonlib.dto.response.PagesResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface PagesMapper {
    @Mapping(source = "pageUrl.displayName", target = "pageUrlDisplayName")
    PagesResponse toResponseDto(Pages pages);

    Pages toEntity(Long id);

    @Mapping(target = "pageUrl", expression = "java(stringToPageUrl(request.getPageUrl()))")
    @Mapping(target = "pageType", expression = "java(stringToPageType(request.getPageType()))")
// audit handled in backend
    Pages dtoToEntity(PagesRequest request);

    // Helpers for enum conversion (safe)
    default PageUrl stringToPageUrl(String url) {
        if (url == null) return null;
        try {
            return PageUrl.valueOf(url.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    default RoleTypes stringToPageType(String type) {
        if (type == null) return null;
        try {
            return RoleTypes.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}

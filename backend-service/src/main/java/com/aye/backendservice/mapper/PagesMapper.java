package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.PagesRequest;
import com.aye.commonlib.dto.response.PagesResponse;
import com.aye.entitylib.entity.Pages;
import com.aye.entitylib.entity.enums.PageUrl;
import com.aye.entitylib.entity.enums.RoleTypes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface PagesMapper {
    @Mapping(source = "pageUrl.displayName", target = "pageUrlDisplayName")
    @Mapping(source = "psysicalName", target = "physicalName")
    PagesResponse toResponseDto(Pages pages);

    Pages toEntity(Long id);

    @Mapping(target = "pageUrl", expression = "java(stringToPageUrl(request.getPageUrl()))")
    @Mapping(target = "pageType", expression = "java(stringToPageType(request.getPageType()))")
    @Mapping(target = "psysicalName", source = "physicalName")
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

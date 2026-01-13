package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.UserSubMenuRequest;
import com.aye.commonlib.dto.response.UserSubMenuResponse;
import com.aye.entitylib.entity.UserSubMenu;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {PagesMapper.class},
        builder = @Builder(disableBuilder = true))
public interface UserSubMenuMapper {
    @Mapping(source = "userMenu.id", target = "userMenuId")
    @Mapping(source = "userMenu.menuName", target = "userMenuName")
    UserSubMenuResponse toResponseDto(UserSubMenu userSubMenu);

    @Mapping(target = "page", source = "pageId")
    UserSubMenu dtoToEntity(UserSubMenuRequest request);
}

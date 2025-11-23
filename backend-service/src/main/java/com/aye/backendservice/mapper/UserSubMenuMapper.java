package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.UserSubMenu;
import com.aye.commonlib.dto.request.UserSubMenuRequest;
import com.aye.commonlib.dto.response.UserSubMenuResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {PagesMapper.class, UserMenuMapper.class},
        builder = @Builder(disableBuilder = true))
public interface UserSubMenuMapper {
    @Mapping(source = "userMenu.id", target = "userMenuId")
    @Mapping(source = "userMenu.menuName", target = "userMenuName")
    UserSubMenuResponse toResponseDto(UserSubMenu userSubMenu);

    UserSubMenu dtoToEntity(UserSubMenuRequest request);
}

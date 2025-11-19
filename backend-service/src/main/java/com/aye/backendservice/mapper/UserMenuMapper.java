package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.UserMenu;
import com.aye.commonlib.dto.response.UserMenuResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {UserSubMenuMapper.class},
        builder = @Builder(disableBuilder = true))
public interface UserMenuMapper {
    @Mapping(source = "appModule.moduleName", target = "moduleName")
    @Mapping(source = "appModule.moduleCode", target = "moduleCode")
    UserMenuResponse toResponseDto(UserMenu userMenu);
}
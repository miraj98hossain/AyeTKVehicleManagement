package com.aye.backendservice.mapper;

import com.aye.commonlib.dto.request.UserMenuRequest;
import com.aye.commonlib.dto.response.UserMenuResponse;
import com.aye.entitylib.entity.UserMenu;
import com.aye.entitylib.entity.enums.MenuLevel;
import com.aye.entitylib.entity.enums.RoleTypes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                UserSubMenuMapper.class,
                AppModuleMapper.class,
        },
        builder = @Builder(disableBuilder = true))
public interface UserMenuMapper {
    @Mapping(source = "appModule.id", target = "moduleId")
    @Mapping(source = "appModule.moduleName", target = "moduleName")
    @Mapping(source = "appModule.moduleCode", target = "moduleCode")
    UserMenuResponse toResponseDto(UserMenu userMenu);

    UserMenu toEntity(Integer id);


    @Mapping(target = "level", expression = "java(stringToMenuLevel(request.getLevel()))")
    @Mapping(target = "pageType", expression = "java(stringToPageType(request.getPageType()))")
    @Mapping(target = "appModule", source = "moduleId")
    UserMenu dtoToEntity(UserMenuRequest request);


    default MenuLevel stringToMenuLevel(String level) {
        if (level == null) return null;
        try {
            return MenuLevel.valueOf(level);
        } catch (Exception e) {
            return null;
        }
    }

    default RoleTypes stringToPageType(String type) {
        if (type == null) return null;
        try {
            return RoleTypes.valueOf(type);
        } catch (Exception e) {
            return null;
        }
    }
}
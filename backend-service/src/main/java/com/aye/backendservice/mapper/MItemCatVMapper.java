package com.aye.backendservice.mapper;


import com.aye.backendservice.postgresEntity.MItemCatVNew;
import com.aye.dto.response.userData.MItemCatVResDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface MItemCatVMapper {
    @Mapping(target = "itemClass", source = "clazz")
    MItemCatVResDto toResponseDto(MItemCatVNew mItemCatV);

    List<MItemCatVResDto> toResponseDto(List<MItemCatVNew> mItemCatV);

}

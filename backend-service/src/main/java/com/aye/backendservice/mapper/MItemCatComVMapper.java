package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.response.MItemCatComVResponse;
import com.aye.entitylib.entity.MItemCatComV;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface MItemCatComVMapper {
    @Mapping(target = "combination", expression = "java(mItemCatComV.getCategory()+\"-\"" +
            "+mItemCatComV.getFamily()" +
            "+\"-\"+mItemCatComV.getItemClass())")
    MItemCatComVResponse toResponseDto(MItemCatComV mItemCatComV);


    MItemCatComV toEntity(Long id);


}

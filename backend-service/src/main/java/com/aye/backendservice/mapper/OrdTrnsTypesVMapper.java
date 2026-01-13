package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.response.OrdTrnsTypesVResponse;
import com.aye.entitylib.entity.OrdTrnsTypesV;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {OrgHierarchyMapper.class,
                InventoryInformationMapper.class,
                ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface OrdTrnsTypesVMapper {
    OrdTrnsTypesVResponse toResponseDto(OrdTrnsTypesV ordTrnsTypesV);
}

package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.om.InventoryInformations;
import com.aye.commonlib.dto.request.InventoryInformationRequest;
import com.aye.commonlib.dto.response.InventoryInformationResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface InventoryInformationMapper {
    @Mapping(source = "orgHierarchy.id", target = "orgHierarchyId")
    @Mapping(source = "orgHierarchy.name", target = "orgHierarchyName")
    @Mapping(source = "orgHierarchy.code", target = "orgHierarchyCode")
    @Mapping(source = "itemOrg.id", target = "itemOrg")
    InventoryInformationResponse toResponseDto(InventoryInformations inventoryInformations);

    InventoryInformations toEntity(Long id);

    InventoryInformations dtoToEntity(InventoryInformationRequest request);
}
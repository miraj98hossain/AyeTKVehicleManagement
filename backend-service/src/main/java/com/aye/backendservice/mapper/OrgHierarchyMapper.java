package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.om.OrgHierarchy;
import com.aye.commonlib.dto.response.OrgHierarchyResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {InventoryInformationMapper.class},
        builder = @Builder(disableBuilder = true))
public interface OrgHierarchyMapper {
    @Mapping(source = "orgHierarchy.id", target = "parentId")
    OrgHierarchyResponse toResponseDto(OrgHierarchy orgHierarchy);
}

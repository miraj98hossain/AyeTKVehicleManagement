package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.OrgHierarchyRequest;
import com.aye.commonlib.dto.response.OrgHierarchyResponse;
import com.aye.entitylib.entity.OrgHierarchy;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {InventoryInformationMapper.class, ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface OrgHierarchyMapper {
    @Mapping(source = "orgHierarchy.id", target = "parentId")
    OrgHierarchyResponse toResponseDto(OrgHierarchy orgHierarchy);

    OrgHierarchy toEntity(Long id);

    OrgHierarchy dtoToEntity(OrgHierarchyRequest orgHierarchyRequest);
}

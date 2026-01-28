package com.aye.backendservice.mapper;

import com.aye.commonlib.dto.request.InvOrgSubInvVRequestDto;
import com.aye.commonlib.dto.response.InvOrgSubInvVResponseDto;
import com.aye.entitylib.entity.InvOrgSubInvV;
import org.mapstruct.*;

/**
 * @author: Miraj
 * @date: 28/01/2026
 * @time: 17:35
 */
@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                OrgHierarchyMapper.class,
                InventoryInformationMapper.class},
        builder = @Builder(disableBuilder = true)
)
public interface InvOrgSubInvVMapper {
    @Mapping(target = "invOrgs", source = "invOrgsId")
    @Mapping(target = "orgHierarchy", source = "orgHierarchyId")
    InvOrgSubInvV toEntity(InvOrgSubInvVRequestDto dto);


    @Mapping(target = "invOrgsId", source = "invOrgs.id")
    @Mapping(target = "orgHierarchyId", source = "orgHierarchy.id")
    InvOrgSubInvVResponseDto toResponseDto(InvOrgSubInvV entity);


    @Mapping(target = "invOrgs", source = "invOrgsId")
    @Mapping(target = "orgHierarchy", source = "orgHierarchyId")
    void updateEntityFromDto(
            InvOrgSubInvVRequestDto dto,
            @MappingTarget InvOrgSubInvV entity
    );
}


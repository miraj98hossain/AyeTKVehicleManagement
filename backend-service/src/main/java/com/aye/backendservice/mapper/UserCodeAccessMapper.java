package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.UserCodeAccess;
import com.aye.commonlib.dto.request.UserCodeAccessRequest;
import com.aye.commonlib.dto.response.UserCodeAccessResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                MUserMapper.class,
                OrgHierarchyMapper.class,
                InventoryInformationMapper.class,},
        builder = @Builder(disableBuilder = true))
public interface UserCodeAccessMapper {

    UserCodeAccess toEntity(Long id);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orgHierarchy.id", target = "orgHierarchyId")
    @Mapping(source = "orgHierarchy.code", target = "orgHierarchyCode")
    @Mapping(source = "inventoryInformations.id", target = "invInfoId")
    @Mapping(source = "inventoryInformations.code", target = "invInfoCode")
    @Mapping(source = "inventoryInformations.name", target = "invInfoName")
    UserCodeAccessResponse toResponseDto(UserCodeAccess userCodeAccess);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "orgId", target = "orgHierarchy")
    @Mapping(source = "invInfoId", target = "inventoryInformations")
    UserCodeAccess dtoToEntity(UserCodeAccessRequest userCodeAccessRequest);

}

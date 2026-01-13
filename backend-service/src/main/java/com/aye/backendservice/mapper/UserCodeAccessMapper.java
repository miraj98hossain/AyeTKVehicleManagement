package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.UserCodeAccessRequest;
import com.aye.commonlib.dto.response.UserCodeAccessResponse;
import com.aye.entitylib.entity.UserCodeAccess;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                MItemCatComVMapper.class,
                MUserMapper.class,
                ScaleSetupMapper.class,
                OrgHierarchyMapper.class,
                InventoryInformationMapper.class,},
        builder = @Builder(disableBuilder = true))
public interface UserCodeAccessMapper {

    UserCodeAccess toEntity(Long id);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.userName", target = "userName")
    @Mapping(source = "orgHierarchy.id", target = "orgHierarchyId")
    @Mapping(source = "orgHierarchy.code", target = "orgHierarchyCode")
    @Mapping(source = "inventoryInformations.id", target = "invInfoId")
    @Mapping(source = "inventoryInformations.code", target = "invInfoCode")
    @Mapping(source = "inventoryInformations.name", target = "invInfoName")
    @Mapping(source = "ScaleSetup.id", target = "scaleSetupId")
    @Mapping(source = "ScaleSetup.name", target = "scaleSetupName")
    UserCodeAccessResponse toResponseDto(UserCodeAccess userCodeAccess);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "orgId", target = "orgHierarchy")
    @Mapping(source = "invInfoId", target = "inventoryInformations")
    @Mapping(source = "scaleSetupId", target = "scaleSetup")
    UserCodeAccess dtoToEntity(UserCodeAccessRequest userCodeAccessRequest);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "orgId", target = "orgHierarchy")
    @Mapping(source = "invInfoId", target = "inventoryInformations")
    @Mapping(source = "scaleSetupId", target = "scaleSetup")
    void dtoToEntity(UserCodeAccessRequest userCodeAccessRequest, @MappingTarget UserCodeAccess userCodeAccess);

}

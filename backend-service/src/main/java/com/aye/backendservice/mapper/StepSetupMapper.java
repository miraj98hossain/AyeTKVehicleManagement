package com.aye.backendservice.mapper;

import com.aye.backendservice.entity.StepSetup;
import com.aye.commonlib.dto.request.StepSetupRequest;
import com.aye.commonlib.dto.response.StepSetupResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                StepMapper.class,
                CommonTypeMapper.class,
                OrgHierarchyMapper.class,
                InventoryInformationMapper.class},
        builder = @Builder(disableBuilder = true))
public interface StepSetupMapper {
    StepSetup dtoToEntity(Long id);

    //Long toId(StepDetails value);
    @Mapping(source = "orgId", target = "org")
    @Mapping(source = "invOrg", target = "invOrg")
    StepSetup dtoToEntity(StepSetupRequest stepSetupRequest);

    @Mapping(source = "org.code", target = "orgCode")
    @Mapping(source = "invOrg.name", target = "invOrgCode")
    @Mapping(source = "org.id", target = "orgId")
    @Mapping(source = "invOrg.id", target = "invOrg")
    StepSetupResponse toResponseDto(StepSetup stepSetup);

    @Mapping(source = "orgId", target = "org")
    @Mapping(source = "invOrg", target = "invOrg")
    void dtoToEntity(StepSetupRequest stepSetupRequest, @MappingTarget StepSetup stepSetup);


//    @Mapping(source = "org.code", target = "orgCode")
//    @Mapping(source = "invOrg.name", target = "invOrgCode")
//    @Mapping(source = "org.id", target = "orgId")
//    @Mapping(source = "invOrg.id", target = "invOrg")
//    StepSetupResponse toResponseDtoWOutDtl(StepSetup stepSetup);
}

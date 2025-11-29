package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.UserAccessInvOrg;
import com.aye.commonlib.dto.request.UserAccessInvOrgRequest;
import com.aye.commonlib.dto.response.UserAccessInvOrgResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {UserTransactionTypesMapper.class,
                InventoryInformationMapper.class,
                ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface UserAccessInvOrgMapper {
    @Mapping(source = "invOrgs.id", target = "invOrgId")
    @Mapping(source = "invOrgs.name", target = "invOrgName")
    @Mapping(source = "userAccessTemltDtl.id", target = "userAccessTemltDtlId")
    @Mapping(source = "userAccessTemltDtl.detailName", target = "tempDtlDetailName")
    @Mapping(source = "userAccessTemltDtl.orgHierarchy.id", target = "orgId")
    UserAccessInvOrgResponse toResponseDto(UserAccessInvOrg userAccessInvOrg);

    UserAccessInvOrg toEntity(Long id);

    @Mapping(source = "invOrgId", target = "invOrgs")
    UserAccessInvOrg dtoToEntity(UserAccessInvOrgRequest userAccessInvOrgRequest);
}

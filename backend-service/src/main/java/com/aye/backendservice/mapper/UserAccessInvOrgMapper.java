package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.UserAccessInvOrg;
import com.aye.commonlib.dto.response.UserAccessInvOrgResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {UserTransactionTypesMapper.class},
        builder = @Builder(disableBuilder = true))
public interface UserAccessInvOrgMapper {
    @Mapping(source = "invOrgs.id", target = "invOrgId")
    UserAccessInvOrgResponse toResponseDto(UserAccessInvOrg userAccessInvOrg);
}

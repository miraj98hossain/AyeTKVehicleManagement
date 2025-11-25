package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.UserAccessTemltDtl;
import com.aye.commonlib.dto.request.UserAccessTemltDtlRequest;
import com.aye.commonlib.dto.response.UserAccessTemltDtlResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {UserMenuMapper.class, UserAccessInvOrgMapper.class},
        builder = @Builder(disableBuilder = true))
public interface UserAccessTemltDtlMapper {
    @Mapping(source = "orgHierarchy.id", target = "orgHierarchyId")
    @Mapping(source = "orgHierarchy.name", target = "orgHierarchyName")
    @Mapping(source = "orgHierarchy.code", target = "orgHierarchyCode")
    @Mapping(source = "requestGroupHeader.groupName", target = "reqGrpHdrName")
    UserAccessTemltDtlResponse toResponseDto(UserAccessTemltDtl userAccessTemltDtl);

    UserAccessTemltDtl dtoToEntity(UserAccessTemltDtlRequest userAccessTemltDtlRequest);
}

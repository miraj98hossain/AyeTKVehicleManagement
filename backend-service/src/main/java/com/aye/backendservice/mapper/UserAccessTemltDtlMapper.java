package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.UserAccessTemltDtl;
import com.aye.commonlib.dto.request.UserAccessTemltDtlRequest;
import com.aye.commonlib.dto.response.UserAccessTemltDtlResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                ReferenceMapper.class,
                UserMenuMapper.class,
                OrgHierarchyMapper.class,
                UserAccessInvOrgMapper.class},
        builder = @Builder(disableBuilder = true))
public interface UserAccessTemltDtlMapper {
    @Mapping(source = "orgHierarchy.id", target = "orgHierarchyId")
    @Mapping(source = "orgHierarchy.name", target = "orgHierarchyName")
    @Mapping(source = "orgHierarchy.code", target = "orgHierarchyCode")
    @Mapping(source = "userAccessTemplt.id", target = "userAccessTemltId")
    @Mapping(source = "userAccessTemplt.tempDesc", target = "userAccessTemltDesc")
    @Mapping(source = "userAccessTemplt.tempNumber", target = "userAccessTemltNumber")
    @Mapping(source = "requestGroupHeader.groupName", target = "reqGrpHdrName")
    @Mapping(target = "startDate", source = "startDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "endDate", source = "endDate", dateFormat = "yyyy-MM-dd")
    UserAccessTemltDtlResponse toResponseDto(UserAccessTemltDtl userAccessTemltDtl);

    @Mapping(target = "startDate", source = "startDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "endDate", source = "endDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "orgHierarchy", source = "orgHierarchyId")
    @Mapping(target = "userMenu", source = "userMenuId")
    UserAccessTemltDtl dtoToEntity(UserAccessTemltDtlRequest userAccessTemltDtlRequest);

    UserAccessTemltDtl toEntity(Integer id);

}

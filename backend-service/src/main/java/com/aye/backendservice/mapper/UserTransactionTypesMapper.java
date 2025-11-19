package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.UserTransactionTypes;
import com.aye.commonlib.dto.response.UserTransactionTypesResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {UserSubInvAccessMapper.class},
        builder = @Builder(disableBuilder = true))
public interface UserTransactionTypesMapper {
    @Mapping(source = "userAccessInvOrg.id", target = "userAccessInvOrgId")
    UserTransactionTypesResponse toResponseDto(UserTransactionTypes userTransactionTypes);
}

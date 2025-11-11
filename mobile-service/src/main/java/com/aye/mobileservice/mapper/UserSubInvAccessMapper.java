package com.aye.mobileservice.mapper;

import com.aye.RestfulServer.model.UserSubInvAccess;
import com.aye.commonlib.dto.response.UserSubInvAccessResponse;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface UserSubInvAccessMapper {
    //@Mapping(source = "userTransactionTypes.id", target = "userTransactionTypeId")
    UserSubInvAccessResponse toResponseDto(UserSubInvAccess userSubInvAccess);
}

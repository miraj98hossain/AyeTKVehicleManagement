package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.UserSubInvAccessRequest;
import com.aye.commonlib.dto.response.UserSubInvAccessResponse;
import com.aye.entitylib.entity.UserSubInvAccess;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface UserSubInvAccessMapper {
    @Mapping(source = "userTransactionTypes.id", target = "userTransactionTypeId")
    UserSubInvAccessResponse toResponseDto(UserSubInvAccess userSubInvAccess);


    UserSubInvAccess dtoToEntity(UserSubInvAccessRequest userSubInvAccessRequest);


    void dtoToEntity(UserSubInvAccessRequest userSubInvAccessRequest, @MappingTarget UserSubInvAccess userSubInvAccess);
}

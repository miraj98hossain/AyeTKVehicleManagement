package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.UserAccess;
import com.aye.commonlib.dto.request.UserAccessRequest;
import com.aye.commonlib.dto.response.UserAccessResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {UserAccessTempltMapper.class},
        builder = @Builder(disableBuilder = true))
public interface UserAccessMapper {
    @Mapping(target = "userId", source = "user.id")
    UserAccessResponse toUserAccessResponse(UserAccess userAccess);

    UserAccess dtoToUserAccess(UserAccessRequest userAccess);
}

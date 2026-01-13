package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.UserAccessRequest;
import com.aye.commonlib.dto.response.UserAccessResponse;
import com.aye.entitylib.entity.UserAccess;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {UserAccessTempltMapper.class},
        builder = @Builder(disableBuilder = true))
public interface UserAccessMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.userName")
    @Mapping(target = "start_date", source = "start_date", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "end_date", source = "end_date", dateFormat = "yyyy-MM-dd")
    UserAccessResponse toResponseDto(UserAccess userAccess);

    @Mapping(target = "start_date", source = "start_date", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "end_date", source = "end_date", dateFormat = "yyyy-MM-dd")
    UserAccess dtoToUserAccess(UserAccessRequest userAccess);
}

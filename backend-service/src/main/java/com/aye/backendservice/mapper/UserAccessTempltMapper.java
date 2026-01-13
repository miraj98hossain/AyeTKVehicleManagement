package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.UserAccessTempltRequest;
import com.aye.commonlib.dto.response.UserAccessTempltResponse;
import com.aye.entitylib.entity.UserAccessTemplt;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {UserAccessTemltDtlMapper.class},
        builder = @Builder(disableBuilder = true))
public interface UserAccessTempltMapper {
    UserAccessTempltResponse toResponseDto(UserAccessTemplt userAccessTemplt);

    UserAccessTemplt dtoToEntity(UserAccessTempltRequest request);
}

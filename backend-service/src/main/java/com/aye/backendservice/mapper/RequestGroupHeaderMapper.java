package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.RequestGroupHeader;
import com.aye.commonlib.dto.request.RequestGroupHeaderRequest;
import com.aye.commonlib.dto.response.RequestGroupHeaderResponse;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {RequestGroupLineMapper.class},
        builder = @Builder(disableBuilder = true))
public interface RequestGroupHeaderMapper {
    RequestGroupHeaderResponse toResponseDto(RequestGroupHeader requestGroupHeader);

    RequestGroupHeader dtoToEntity(RequestGroupHeaderRequest reqGrpHdrReq);
}

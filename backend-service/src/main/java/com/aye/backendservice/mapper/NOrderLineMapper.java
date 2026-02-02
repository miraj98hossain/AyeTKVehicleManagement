package com.aye.backendservice.mapper;

import com.aye.commonlib.dto.response.NOrderLineResponse;
import com.aye.entitylib.entity.order.NorderLine;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @author: Miraj
 * @date: 15/01/2026
 * @time: 12:20
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface NOrderLineMapper {
    NOrderLineResponse toResponseDto(NorderLine nOrderHeader);
}

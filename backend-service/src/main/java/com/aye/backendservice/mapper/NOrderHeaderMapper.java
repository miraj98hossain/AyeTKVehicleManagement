package com.aye.backendservice.mapper;

import com.aye.commonlib.dto.response.NOrderHeaderResponse;
import com.aye.entitylib.entity.order.NorderHeader;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @author: Miraj
 * @date: 15/01/2026
 * @time: 12:19
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                NOrderLineMapper.class},
        builder = @Builder(disableBuilder = true))
public interface NOrderHeaderMapper {

    NOrderHeaderResponse toResponseDto(NorderHeader nOrderHeader);
}

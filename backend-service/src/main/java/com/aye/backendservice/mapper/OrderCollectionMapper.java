package com.aye.backendservice.mapper;

import com.aye.commonlib.dto.response.OrderCollectionResponse;
import com.aye.entitylib.entity.OrderCollection;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @author: Miraj
 * @date: 17/01/2026
 * @time: 12:43
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class, NOrderHeaderMapper.class},
        builder = @Builder(disableBuilder = true))
public interface OrderCollectionMapper {
    OrderCollectionResponse toResponseDto(OrderCollection orderCollection);
}

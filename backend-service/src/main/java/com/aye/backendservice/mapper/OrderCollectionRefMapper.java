package com.aye.backendservice.mapper;

import com.aye.commonlib.dto.response.OrderCollectionRefResponse;
import com.aye.entitylib.entity.order.OrderCollection;
import org.mapstruct.*;

/**
 * @author: Miraj
 * @date: 17/01/2026
 * @time: 13:48
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface OrderCollectionRefMapper {
    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "collectionId", source = "collection.collectionId")
    OrderCollectionRefResponse toResponseDto(OrderCollection orderCollection);
}

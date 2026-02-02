package com.aye.backendservice.mapper;

import com.aye.commonlib.dto.response.OnlineCollectionResponse;
import com.aye.entitylib.entity.order.OnlineCollection;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @author: Miraj
 * @date: 17/01/2026
 * @time: 12:40
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                OrderCollectionRefMapper.class},
        builder = @Builder(disableBuilder = true))
public interface OnlineCollectionMapper {
    OnlineCollectionResponse toResponseDto(OnlineCollection onlineCollection);

}

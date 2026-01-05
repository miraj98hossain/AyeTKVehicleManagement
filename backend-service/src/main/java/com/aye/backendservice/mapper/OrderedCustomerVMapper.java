package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.om.OrderedCustomerV;
import com.aye.commonlib.dto.response.OrderedCustomerVResponse;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 10:10
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface OrderedCustomerVMapper {
    
    OrderedCustomerVResponse toResponseDto(OrderedCustomerV orderedCustomerV);
}

package com.aye.backendservice.mapper;

import com.aye.backendservice.entity.IPInfo;
import com.aye.commonlib.dto.response.IPInfoResponse;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 16:20
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface IPInfoMapper {
    IPInfoResponse toIPInfoResponse(IPInfo ipInfo);
}

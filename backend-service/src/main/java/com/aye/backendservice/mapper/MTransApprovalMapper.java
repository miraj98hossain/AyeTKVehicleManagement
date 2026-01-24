package com.aye.backendservice.mapper;

import com.aye.commonlib.dto.response.MTransApprovalResponse;
import com.aye.entitylib.entity.MTransApproval;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @author: Miraj
 * @date: 15/01/2026
 * @time: 12:46
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface MTransApprovalMapper {
    MTransApprovalResponse toResponseDto(MTransApproval mTransApproval);
}

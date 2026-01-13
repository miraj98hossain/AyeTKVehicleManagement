package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.StepRequest;
import com.aye.commonlib.dto.response.StepResponse;
import com.aye.entitylib.entity.Step;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface StepMapper {
    Step toEntity(Long id);

    //Long toId(Step value);
    Step toEntity(StepRequest stepRequest);

    StepResponse toResponseDto(Step step);

}

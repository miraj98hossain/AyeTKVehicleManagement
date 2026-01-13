package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.MUserRequest;
import com.aye.commonlib.dto.response.MUserResponse;
import com.aye.entitylib.entity.Muser;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                InventoryInformationMapper.class,
                MRoleMapper.class},
        builder = @Builder(disableBuilder = true))
public interface MUserMapper {

    MUserResponse toResponseDto(Muser muser);

    Muser toEntity(Integer id);

    Muser dtoToEntity(MUserRequest mUserRequest);
}

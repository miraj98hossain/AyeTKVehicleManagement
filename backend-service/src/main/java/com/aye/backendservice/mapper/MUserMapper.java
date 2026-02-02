package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.MUserRequest;
import com.aye.commonlib.dto.response.MUserResponse;
import com.aye.entitylib.entity.user.Muser;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                InventoryInformationMapper.class,
                MRoleMapper.class},
        builder = @Builder(disableBuilder = true))
public interface MUserMapper {

    MUserResponse toResponseDto(Muser muser);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "autoNumber", ignore = true)
    @Mapping(target = "roles", ignore = true)
    MUserResponse toResponseDtoWithLessInfo(Muser muser);

    Muser toEntity(Integer id);

    Muser dtoToEntity(MUserRequest mUserRequest);
}

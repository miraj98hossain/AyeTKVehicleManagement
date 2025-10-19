package com.mhdev.backendservice.mapper;

import com.mhdev.backendservice.entity.AppUser;
import com.mhdev.commonlib.dto.request.RegistrationReqDto;
import com.mhdev.commonlib.dto.response.RegistrationResDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AppUser toAppUser(RegistrationReqDto registrationDto);
    RegistrationResDto toRegistrationResDto(AppUser appUser);
}

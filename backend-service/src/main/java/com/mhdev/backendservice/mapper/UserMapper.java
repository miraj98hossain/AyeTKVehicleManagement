package com.mhdev.backendservice.mapper;

import com.mhdev.backendservice.dto.requestdto.RegistrationReqDto;
import com.mhdev.backendservice.dto.responsedto.RegistrationResDto;
import com.mhdev.backendservice.entity.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AppUser toAppUser(RegistrationReqDto registrationDto);
    RegistrationResDto toRegistrationResDto(AppUser appUser);
}

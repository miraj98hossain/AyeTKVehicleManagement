package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.UserTransactionTypes;
import com.aye.commonlib.dto.request.UserTransactionTypesRequest;
import com.aye.commonlib.dto.response.UserTransactionTypesResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {UserSubInvAccessMapper.class, ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface UserTransactionTypesMapper {
    @Mapping(source = "userAccessInvOrg.id", target = "userAccessInvOrgId")
    @Mapping(target = "startDate", source = "startDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "endDate", source = "endDate", dateFormat = "yyyy-MM-dd")
    UserTransactionTypesResponse toResponseDto(UserTransactionTypes userTransactionTypes);

    UserTransactionTypes dtoToEntity(UserTransactionTypesRequest request);

    UserTransactionTypes toEntity(Long id);

}

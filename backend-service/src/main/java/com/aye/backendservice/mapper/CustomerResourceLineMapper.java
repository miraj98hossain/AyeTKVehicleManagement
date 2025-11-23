//package com.aye.backendservice.mapper;
//
//import com.aye.commonlib.dto.request.CustomerResourceHeaderRequest;
//import org.mapstruct.Builder;
//import org.mapstruct.InjectionStrategy;
//import org.mapstruct.Mapper;
//import org.mapstruct.NullValueCheckStrategy;
//
//@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
//        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
//        uses = {CustomerResourceHeaderRequest.class},
//        builder = @Builder(disableBuilder = true))
//public interface CustomerResourceLineMapper {
//
//    // Entity -> Response DTO
//    @Mapping(target = "resourceStatus", expression = "java(line.getResourceStatus() != null ? line.getResourceStatus().name() : null)")
//    @Mapping(target = "creationDate", expression = "java(dateToString(line.getCreationDate()))")
//    @Mapping(target = "lastUpdateDate", expression = "java(dateToString(line.getLastUpdateDate()))")
//    @Mapping(target = "lastUpdateLogin", ignore = true)
//    @Mapping(target = "empResourceId", source = "empResource.id")
//    // if exists
//    CustomerResourceLineResponse toResponse(CustomerResourceLine line);
//
//    // Request DTO -> Entity
//    @Mapping(target = "resourceStatus", expression = "java(stringToEnum(request.getResourceStatus()))")
//    @Mapping(target = "customerResourceHeader", ignore = true) // will set in service layer
//    @Mapping(target = "empResource", ignore = true) // must be fetched before saving
//    @Mapping(target = "createdBy", ignore = true)
//    @Mapping(target = "lastUpdateBy", ignore = true)
//    @Mapping(target = "creationDate", ignore = true)
//    @Mapping(target = "lastUpdateDate", ignore = true)
//    @Mapping(target = "lastUpdateLogin", ignore = true)
//    CustomerResourceLine toEntity(CustomerResourceLineRequest request);
//
//    // Helpers
//    default String dateToString(Date date) {
//        if (date == null) return null;
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//    }
//
//    default RegularData.ResourceStatus stringToEnum(String status) {
//        if (status == null) return null;
//        try {
//            return RegularData.ResourceStatus.valueOf(status);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}
